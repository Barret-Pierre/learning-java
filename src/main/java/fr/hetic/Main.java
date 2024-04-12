package fr.hetic;

import fr.hetic.database.DatabaseManager;
import fr.hetic.processors.DatabaseProcessOperation;
import fr.hetic.processors.DirectoryProcessOperation;
import fr.hetic.readers.ReaderDatabase;
import fr.hetic.readers.ReaderDirectory;
import fr.hetic.statements.FileStatement;
import fr.hetic.writers.WriterResultFileStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final String OUTPUT_DIRECTORY = "./outputs/";
    private static final Properties properties = new Properties();


    public static void main(String[] args) throws IOException {
        properties.load(new FileInputStream("./src/main/resources/application.properties"));
        String TYPE = properties.getProperty("data.type");

        Calculator calculator = new Calculator();
        FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION, OUTPUT_DIRECTORY);

        if(Objects.equals(TYPE, "FILE")) {

            String OPERATIONS_DIRECTORY = properties.getProperty("data.directory");

            DirectoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);
            ReaderDirectory reader = new ReaderDirectory(OPERATIONS_DIRECTORY, fileUtils);
            DirectoryProcessOperation directoryProcessOperation = new DirectoryProcessOperation(calculator, new WriterResultFileStrategy() ,reader, fileUtils);
            directoryProcessOperation.startProcess();

        } else if (Objects.equals(TYPE, "JDBC")) {

            String HOST_DATABASE = properties.getProperty("data.url");
            String USER_DATABASE = properties.getProperty("data.user");
            String PASSWORD_DATABASE = properties.getProperty("data.password");

            DatabaseManager databaseManager = new DatabaseManager(HOST_DATABASE, USER_DATABASE, PASSWORD_DATABASE);
            ReaderDatabase reader = new ReaderDatabase(databaseManager, new FileStatement(databaseManager));
            DatabaseProcessOperation databaseProcessOperation = new DatabaseProcessOperation(calculator, new WriterResultFileStrategy(), reader, fileUtils);
            databaseProcessOperation.startProcess();

        }

    }
}
