package fr.hetic;

import java.util.ArrayList;
import java.util.List;


public class ProcessOperation {

    private final Calculator calculator;
    private final FileUtils fileUtils;
    private final String resultFilePath;

    public ProcessOperation(Calculator calculator, FileUtils fileUtils, String resultFilePath) {
        this.calculator = calculator;
        this.fileUtils = fileUtils;
        this.resultFilePath = resultFilePath;
    }

    public void createResultFile(List<String> resultLines) {
        for (String result : resultLines) {
            fileUtils.writeLineInFile(resultFilePath, result);
        }
    }

    public List<String> getResultOfContentFile(List<String> contentFile) {
        List<String> resultLines = new ArrayList<>();
        for (String line : contentFile) {
            Line lineInput = new Line(line);
            String result = "ERROR\n";
            if (lineInput.isValid) {
                result = calculator.calculateResult(lineInput.number_1, lineInput.number_2, lineInput.operator) + "\n";
            }
            resultLines.add(result);
        }
        return resultLines;
    }

}
