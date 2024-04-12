package fr.hetic;

import fr.hetic.database.DatabaseManager;
import fr.hetic.processors.DatabaseProcessOperation;
import fr.hetic.processors.DirectoryProcessOperation;
import fr.hetic.readers.ReaderDatabase;
import fr.hetic.readers.ReaderDirectory;
import fr.hetic.writers.WriterResultFileStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {
    private static final Properties properties = new Properties();


    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        properties.load(new FileInputStream("./src/main/resources/application.properties"));
        String TYPE = properties.getProperty("data.type");

        Calculator calculator = new Calculator();
        FileUtils fileUtils = context.getBean(FileUtils.class);

        if(Objects.equals(TYPE, "FILE")) {

            String OPERATIONS_DIRECTORY = properties.getProperty("data.directory");

            DirectoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);
            ReaderDirectory reader = context.getBean(ReaderDirectory.class);
            DirectoryProcessOperation directoryProcessOperation = new DirectoryProcessOperation(calculator, new WriterResultFileStrategy() ,reader, fileUtils);
            directoryProcessOperation.startProcess();

        } else if (Objects.equals(TYPE, "JDBC")) {

            ReaderDatabase reader = context.getBean(ReaderDatabase.class);
            DatabaseProcessOperation databaseProcessOperation = new DatabaseProcessOperation(calculator, new WriterResultFileStrategy(), reader, fileUtils);
            databaseProcessOperation.startProcess();

        }

    }
}
