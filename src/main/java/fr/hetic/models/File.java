package fr.hetic.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class File {
    private final String fileName;
    private final Path path;
    private final List<Line> lines = new ArrayList<>();

    public File(String fileName, Path path) {
        this.fileName = fileName;
        this.path = path;
    }

    public List<Line> getLines() {
        return lines;
    }

    public String getFileName() {
        return fileName;
    }

    public Path getPath() {
        return path;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
