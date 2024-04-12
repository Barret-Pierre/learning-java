package fr.hetic;


import fr.hetic.database.DatabaseManager;
import fr.hetic.models.File;
import fr.hetic.models.Line;
import fr.hetic.statements.FileStatement;
import fr.hetic.writers.WriterResultFileStrategy;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class Main {
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final String OUTPUT_DIRECTORY = "./outputs/";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        ProcessorStrategy processorStrategy = new ProcessorStrategy(args);

        if(Objects.equals(processorStrategy.TYPE, "FILE")) {
            DirectoryUtils directoryUtils = new DirectoryUtils();
            Calculator calculator = new Calculator();
            FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION, OUTPUT_DIRECTORY);
            ProcessOperation processOperation = new ProcessOperation(calculator, new WriterResultFileStrategy());

            String OPERATIONS_DIRECTORY = processorStrategy.DIRECTORY;

            directoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);

            fileUtils.getFilesByExtensionInDirectory(OPERATIONS_DIRECTORY).forEach(path-> {
                List<String> contentFile = fileUtils.getContentOfFile(path);
                List<Line> lines = processOperation.mapContentFileLines(contentFile);
                Path resultFilePath = fileUtils.createResultFilePathWithPath(path);
                fileUtils.deleteFileIfExist(resultFilePath);
                List<String> resultLines = processOperation.getResultsOfData(lines);
                processOperation.createResultFile(resultLines, resultFilePath);
            });
        } else if (Objects.equals(processorStrategy.TYPE, "JDBC")) {
            //String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";

            Calculator calculator = new Calculator();
            FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION, OUTPUT_DIRECTORY);
            ProcessOperation processOperation = new ProcessOperation(calculator, new WriterResultFileStrategy());

            DatabaseManager databaseManager = new DatabaseManager(processorStrategy.HOST_DATABASE, processorStrategy.USER_DATABASE, processorStrategy.PASSWORD_DATABASE);
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
}
