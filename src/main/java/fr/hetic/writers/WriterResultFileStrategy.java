package fr.hetic.writers;

import java.io.FileWriter;
import java.nio.file.Path;

public class WriterResultFileStrategy implements WriterStrategy {
    @Override
    public void writeLineInFile(Path finalFilePath, String line) {
            try (FileWriter myWriter = new FileWriter(finalFilePath.toString(), true)) {
                myWriter.write(line);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
