package fr.hetic;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileUtils {

    private final String RESULT_FILE_EXTENSION;
    private final String INPUT_FILE_EXTENSION;


    public FileUtils(String resultFileExtension, String inputFileExtension) {
        this.RESULT_FILE_EXTENSION = resultFileExtension;
        this.INPUT_FILE_EXTENSION = inputFileExtension;
    }

    public Path createResultFilePath(Path inputFilePath) {
        String inputPathString = inputFilePath.toString();
        return Path.of(inputPathString.replace(INPUT_FILE_EXTENSION, RESULT_FILE_EXTENSION));
    }

    public void deleteFileIfExist(Path filePath) {
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<String> getContentOfFile(Path filePath) {
        List<String> contentFileInArray = new ArrayList<>(List.of());
        try (Scanner scanner = new Scanner(filePath)) {
            while (scanner.hasNextLine()) {
                contentFileInArray.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return contentFileInArray;
    }

    public List<Path> getFilesByExtensionInDirectory(String directoryPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return paths.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(INPUT_FILE_EXTENSION)).toList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }
}
