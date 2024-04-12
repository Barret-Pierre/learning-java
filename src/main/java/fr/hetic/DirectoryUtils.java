package fr.hetic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryUtils {


    public static void isDirectoryExist(String directoryPathString) {
        try {
            Path directoryPath = Paths.get(directoryPathString);
            if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
                throw new Exception("Directory path doesn't exist or invalid must be absolute");

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    public static void verifyDirectoryArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Number of directory argument passed is incorrect");
            System.exit(1);
        }
    }

}
