package fr.hetic.readers;

import fr.hetic.DirectoryUtils;
import fr.hetic.FileUtils;
import fr.hetic.models.File;
import fr.hetic.models.Line;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReaderDirectory implements ReaderStrategy {
    public String OPERATIONS_DIRECTORY;
    private final FileUtils fileUtils;

    public ReaderDirectory(String OPERATIONS_DIRECTORY, FileUtils fileUtils) {
        this.OPERATIONS_DIRECTORY = OPERATIONS_DIRECTORY;
        this.fileUtils = fileUtils;

    }

    @Override
    public List<File> getFile() {
        List<File> data = new ArrayList<>();
        fileUtils.getFilesByExtensionInDirectory(OPERATIONS_DIRECTORY).forEach(path-> {
            List<String> contentFile = fileUtils.getContentOfFile(path);
            File file = new File(path.getFileName().toString(), path);
            for (String contentLine : contentFile) {
                if (Line.verifyLineInput(contentLine)) {
                    int number_1 = Integer.parseInt(contentLine.split(StringUtils.SPACE)[0]);
                    int number_2 = Integer.parseInt(contentLine.split(StringUtils.SPACE)[1]);
                    String operator = contentLine.split(StringUtils.SPACE)[2];
                    Line line = new Line(number_1, number_2, operator);
                    file.addLine(line);
                } else {
                    Line line = new Line(null, null, null);
                    file.addLine(line);
                }
            }

            data.add(file);
        });
        return data;
    }
}
