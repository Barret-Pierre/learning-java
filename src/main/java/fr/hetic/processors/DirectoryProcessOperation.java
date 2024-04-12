package fr.hetic.processors;

import fr.hetic.Calculator;
import fr.hetic.FileUtils;
import fr.hetic.models.File;
import fr.hetic.readers.ReaderDirectory;
import fr.hetic.writers.WriterStrategy;

import java.nio.file.Path;
import java.util.List;

public class DirectoryProcessOperation extends ProcessOperation implements ProcessOperationStrategy {
        private final ReaderDirectory reader;
        private final FileUtils fileUtils;

        public DirectoryProcessOperation(Calculator calculator, WriterStrategy writer, ReaderDirectory reader, FileUtils fileUtils) {
            super(calculator, writer);
            this.reader = reader;
            this.fileUtils = fileUtils;
        }

        @Override
        public void startProcess() {
            List<File> files = reader.getFile();

            for(File file : files) {
                Path resultFilePath = fileUtils.createResultFilePathWithPath(file.getPath());
                fileUtils.deleteFileIfExist(resultFilePath);
                List<String> resultLines = getResultsOfData(file.getLines());
                createResultFile(resultLines, resultFilePath);
        }
    }
}
