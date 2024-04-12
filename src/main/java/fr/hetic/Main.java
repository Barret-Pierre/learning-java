package fr.hetic;

import fr.hetic.database.DatabaseManager;
import fr.hetic.processors.DatabaseProcessOperation;
import fr.hetic.processors.DirectoryProcessOperation;
import fr.hetic.readers.ReaderDatabase;
import fr.hetic.readers.ReaderDirectory;
import fr.hetic.statements.FileStatement;
import fr.hetic.writers.WriterResultFileStrategy;

import java.sql.*;
import java.util.Objects;

public class Main {
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final String OUTPUT_DIRECTORY = "./outputs/";

    public static void main(String[] args) throws SQLException {

        ProcessorStrategy processorStrategy = new ProcessorStrategy(args);
        Calculator calculator = new Calculator();
        FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION, OUTPUT_DIRECTORY);

        if(Objects.equals(processorStrategy.TYPE, "FILE")) {

            String OPERATIONS_DIRECTORY = processorStrategy.DIRECTORY;
            DirectoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);
            ReaderDirectory reader = new ReaderDirectory(OPERATIONS_DIRECTORY, fileUtils);
            DirectoryProcessOperation directoryProcessOperation = new DirectoryProcessOperation(calculator, new WriterResultFileStrategy() ,reader, fileUtils);
            directoryProcessOperation.startProcess();

        } else if (Objects.equals(processorStrategy.TYPE, "JDBC")) {

            DatabaseManager databaseManager = new DatabaseManager(processorStrategy.HOST_DATABASE, processorStrategy.USER_DATABASE, processorStrategy.PASSWORD_DATABASE);
            ReaderDatabase reader = new ReaderDatabase(databaseManager, new FileStatement(databaseManager));
            DatabaseProcessOperation databaseProcessOperation = new DatabaseProcessOperation(calculator, new WriterResultFileStrategy(), reader, fileUtils);
            databaseProcessOperation.startProcess();

        }

    }
}
