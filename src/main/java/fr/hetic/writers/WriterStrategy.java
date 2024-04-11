package fr.hetic.writers;

import java.nio.file.Path;

public interface WriterStrategy {
    void writeLineInFile(Path finalFilePath, String line);
}
