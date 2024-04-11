package fr.hetic;


import fr.hetic.database.DatabaseManager;
import fr.hetic.models.File;
import fr.hetic.models.Line;
import fr.hetic.statements.FileStatement;
import fr.hetic.writers.WriterResultFileStrategy;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

public class Main {
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final String OUTPUT_DIRECTORY = "./outputs/";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        DirectoryUtils directoryUtils = new DirectoryUtils();
        Calculator calculator = new Calculator();
        FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION, OUTPUT_DIRECTORY);
        ProcessOperation processOperation = new ProcessOperation(calculator, new WriterResultFileStrategy());
        /*
        directoryUtils.verifyDirectoryArgs(args);

        String OPERATIONS_DIRECTORY = args[0];

        directoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);

        fileUtils.getFilesByExtensionInDirectory(OPERATIONS_DIRECTORY).forEach(path-> {
            List<String> contentFile = fileUtils.getContentOfFile(path);
            Path resultFilePath = fileUtils.createResultFilePathWithPath(path);
            System.out.println(resultFilePath);});
            fileUtils.deleteFileIfExist(resultFilePath);
            List<String> resultLines = processOperation.getResultsOfData(contentFile);
            processOperation.createResultFile(resultLines, resultFilePath);
        });
*/

        //String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String url = "jdbc:postgresql://127.0.0.1:5432/TP";
        String user = "etudiant";
        String password = "MT4@hetic2324";

        DatabaseManager databaseManager = new DatabaseManager(url, user, password);
        List<File> files = new FileStatement(databaseManager).getFilesWithLinesByType();
        for(File file : files) {
            List<Line> lines = file.getLines();
            Path resultFilePath = fileUtils.createResultFilePathWithFileName(file.getFileName());
            fileUtils.deleteFileIfExist(resultFilePath);
            fileUtils.createResultFileAndDirectory(resultFilePath);
            List<String> resultLines = processOperation.getResultsOfData(lines);
            processOperation.createResultFile(resultLines, resultFilePath);
        }
    }
}
