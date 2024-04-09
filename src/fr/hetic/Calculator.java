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
    private static final String OPERATIONS_DIRECTORY = "./operations/";
    private static final String INPUT_FILE_EXTENSION = ".op";
    private static final String RESULT_FILE_EXTENSION = ".res";
    private static final List<String> SUPPORTED_OPERATORS = Arrays.asList("+", "-", "*");

    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(OPERATIONS_DIRECTORY))) {
            paths.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(INPUT_FILE_EXTENSION)).forEach(Calculator::processOperationFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void processOperationFile(Path path) {
        Path resultFilePath = createResultFilePath(path);
        deleteFileIfExist(resultFilePath);
        try (Scanner obj = new Scanner(path)) {
            while (obj.hasNextLine()) {
                String[] lineArgs = lineToArgsArray(obj.nextLine());
                try {
                    if (verifyArgs(lineArgs)) {
                        Integer number_1 = Integer.parseInt(lineArgs[0]);
                        Integer number_2 = Integer.parseInt(lineArgs[1]);
                        String operator = lineArgs[2];

                        String resultToString = calculateResult(number_1, number_2, operator) + "\n";
                        writeLineInFile(resultFilePath.toString(), resultToString);
                    } else {
                        String error = "ERROR\n";
                        writeLineInFile(resultFilePath.toString(), error);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
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

    public static Boolean verifyArgs(String[] args) {
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

    public static String[] lineToArgsArray(String line) {
        return line.split(" ");
    }

    public static void writeLineInFile(String filePath, String lineToWrite) {
        try (FileWriter myWriter = new FileWriter(filePath, true)) {
            myWriter.write(lineToWrite);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createResultFilePath(Path inputFilePath) {
        String fileName = inputFilePath.getFileName().toString();
        return Path.of(OPERATIONS_DIRECTORY + fileName.replace(INPUT_FILE_EXTENSION, RESULT_FILE_EXTENSION));
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
