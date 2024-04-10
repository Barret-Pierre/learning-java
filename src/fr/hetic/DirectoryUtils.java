package fr.hetic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryUtils {


    public void isDirectoryExist(String directoryPathString) {
        Path directoryPath = Paths.get(directoryPathString);
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.err.println("Directory path doesn't exist or invalid must be absolute");
            System.exit(1);
        }
    }

    public void verifyDirectoryArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Number of directory argument passed is incorrect");
            System.exit(1);
        }
    }

}
