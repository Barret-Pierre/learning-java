package fr.hetic;

import java.nio.file.Path;
import java.util.List;

public interface WriterStrategy {
    void writeLineInFile(Path finalFilePath, String line);
}
