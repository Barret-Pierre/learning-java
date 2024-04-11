package fr.hetic;

import java.nio.file.Path;
import java.util.List;

public class Main {

    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";

    public static void main(String[] args) {
        DirectoryUtils directoryUtils = new DirectoryUtils();
        Calculator calculator = new Calculator();
        FileUtils fileUtils = new FileUtils(RESULT_FILE_EXTENSION, INPUT_FILE_EXTENSION);
        ProcessOperation processOperation = new ProcessOperation(calculator, new WriterResultFileStrategy());

        directoryUtils.verifyDirectoryArgs(args);

        String OPERATIONS_DIRECTORY = args[0];

        directoryUtils.isDirectoryExist(OPERATIONS_DIRECTORY);

        fileUtils.getFilesByExtensionInDirectory(OPERATIONS_DIRECTORY).forEach(path-> {
            List<String> contentFile = fileUtils.getContentOfFile(path);
            Path resultFilePath = fileUtils.createResultFilePath(path);
            fileUtils.deleteFileIfExist(resultFilePath);
            List<String> resultLines = processOperation.getResultsOfData(contentFile);
            processOperation.createResultFile(resultLines, resultFilePath);
        });
    }
}
