package fr.hetic;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Calculator {
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final List<String> SUPPORTED_OPERATORS = Arrays.asList("+", "-", "*");

    public static void main(String[] args) {
        verifyDirectoryArgs(args);

        String OPERATIONS_DIRECTORY = args[0];

        isDirectoryExist(OPERATIONS_DIRECTORY);

        try (Stream<Path> paths = Files.walk(Paths.get(OPERATIONS_DIRECTORY))) {
            paths.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(INPUT_FILE_EXTENSION)).forEach(Calculator::processOperationFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void isDirectoryExist(String directoryPathString) {
        Path directoryPath = Paths.get(directoryPathString);
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.err.println("Directory path doesn't exist or invalid must be absolute");
            System.exit(1);
        }

    }

    public static void verifyDirectoryArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Number of directory argument passed is incorrect");
            System.exit(1);
        }
    }

    public static void processOperationFile(Path path) {
        System.out.println(path);
        Path resultFilePath = createResultFilePath(path);
        deleteFileIfExist(resultFilePath);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String[] lineArgs = scanner.nextLine().split(" ");
                String result = "ERROR\n";

                if (verifyCalculateArgs(lineArgs)) {
                    Integer number_1 = Integer.parseInt(lineArgs[0]);
                    Integer number_2 = Integer.parseInt(lineArgs[1]);
                    String operator = lineArgs[2];

                    result = calculateResult(number_1, number_2, operator) + "\n";
                }

                writeLineInFile(resultFilePath.toString(), result);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static Integer calculateResult(Integer number_1, Integer number_2, String operator) {
        return switch (operator) {
            case "+" -> number_1 + number_2;
            case "-" -> number_1 - number_2;
            case "*" -> number_1 * number_2;
            default -> null;
        };
    }

    public static Boolean verifyCalculateArgs(String[] args) {
        try {
            if (args.length != 3) {
                throw new Exception("Number of args incorrect");
            }

            if (!SUPPORTED_OPERATORS.contains(args[2])) {
                throw new Exception("Operator not supported");
            }

            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static void writeLineInFile(String filePath, String lineToWrite) {
        try (FileWriter myWriter = new FileWriter(filePath, true)) {
            myWriter.write(lineToWrite);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createResultFilePath(Path inputFilePath) {
        String inputPathString = inputFilePath.toString();
        System.out.println(inputPathString);
        return Path.of(inputPathString.replace(INPUT_FILE_EXTENSION, RESULT_FILE_EXTENSION));
    }

    public static void deleteFileIfExist(Path filePath) {
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
