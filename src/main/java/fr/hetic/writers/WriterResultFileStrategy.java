package fr.hetic.writers;

import java.io.FileWriter;
import java.nio.file.Path;

public class WriterResultFileStrategy implements WriterStrategy {
    @Override
    public void writeLineInFile(Path finalFilePath, String line) {
        System.out.println(line);
        System.out.println(finalFilePath);
            try (FileWriter myWriter = new FileWriter(finalFilePath.toString(), true)) {
                myWriter.write(line);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
