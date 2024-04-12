package fr.hetic.statements;

import fr.hetic.database.DatabaseManager;
import fr.hetic.models.File;
import fr.hetic.models.Line;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FileStatement {

    private final Connection connection;
    private final DatabaseManager databaseManager;


    public FileStatement(DatabaseManager databaseManager) {
        this.connection = databaseManager.getConnection();
        this.databaseManager = databaseManager;
    }

    public List<File> getFilesWithLinesByType() {
        List<File> data = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            String selectSql = "SELECT * FROM LIGNE AS l INNER JOIN FICHIER AS f ON l.FICHIER_ID = f.ID WHERE f.TYPE='OP' ORDER BY f.NOM, l.POSITION ASC";
            try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    int param1 = resultSet.getInt("PARAM1");
                    int param2 = resultSet.getInt("PARAM2");
                    String operator = resultSet.getString("OPERATEUR");
                    String fileName = resultSet.getString("NOM").trim();

                    Line line = new Line(param1, param2, operator);
                    long isExist = data.stream().filter(fileData -> Objects.equals(fileData.getFileName(), fileName)).count();
                    if(data.isEmpty() || isExist == 0) {
                        File file = new File(fileName, null);
                        file.addLine(line);
                        data.add(file);
                    } else {
                        for (File file: data) {
                            if (Objects.equals(file.getFileName(), fileName)) {
                                file.addLine(line);
                            }
                        }
                    }

                }
            }
            databaseManager.closeConnection(connection);
            return data;
        } catch (SQLException e) {
            databaseManager.closeConnection(connection);
            throw new RuntimeException(e);
        }
    }
}
