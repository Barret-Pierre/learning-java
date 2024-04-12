package fr.hetic.readers;

import fr.hetic.database.DatabaseManager;
import fr.hetic.models.File;
import fr.hetic.statements.FileStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReaderDatabase implements ReaderStrategy{
    DatabaseManager databaseManager;
    FileStatement fileStatement;

    public ReaderDatabase(DatabaseManager databaseManager, FileStatement fileStatement) {
        this.databaseManager = databaseManager;
        this.fileStatement = fileStatement;

    }

    @Override
    public List<File> getFile() {
        return fileStatement.getFilesWithLinesByType();
    }
}
