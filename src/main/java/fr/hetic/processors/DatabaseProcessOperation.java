package fr.hetic.processors;

import fr.hetic.Calculator;
import fr.hetic.FileUtils;
import fr.hetic.models.File;
import fr.hetic.models.Line;
import fr.hetic.readers.ReaderDatabase;
import fr.hetic.readers.ReaderDirectory;
import fr.hetic.writers.WriterStrategy;

import java.nio.file.Path;
import java.util.List;

public class DatabaseProcessOperation extends ProcessOperation implements ProcessOperationStrategy  {
    private final ReaderDatabase reader;
    private final FileUtils fileUtils;

    public DatabaseProcessOperation(Calculator calculator, WriterStrategy writer, ReaderDatabase reader, FileUtils fileUtils) {
        super(calculator, writer);
        this.reader = reader;
        this.fileUtils = fileUtils;
    }

    @Override
    public void startProcess() {
        List<File> files = reader.getFile();

        for(File file : files) {
            List<Line> lines = file.getLines();
            Path resultFilePath = fileUtils.createResultFilePathWithFileName(file.getFileName());
            fileUtils.deleteFileIfExist(resultFilePath);
            fileUtils.createResultFileAndDirectory(resultFilePath);
            List<String> resultLines = getResultsOfData(lines);
            createResultFile(resultLines, resultFilePath);
        }

    }
}
