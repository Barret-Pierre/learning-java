package fr.hetic.models;

import java.util.ArrayList;
import java.util.List;

public class File {
    private final String fileName;
    private final List<Line> lines = new ArrayList<>();

    public File(String fileName) {
        this.fileName = fileName;
    }

    public List<Line> getLines() {
        return lines;
    }

    public String getFileName() {
        return fileName;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
