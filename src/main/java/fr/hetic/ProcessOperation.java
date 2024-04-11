package fr.hetic;

import fr.hetic.writers.WriterStrategy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ProcessOperation {

    private final Calculator calculator;
    private final WriterStrategy writer ;


    public ProcessOperation(Calculator calculator, WriterStrategy writer) {
        this.calculator = calculator;
        this.writer = writer;

    }

    public void createResultFile(List<String> resultLines, Path resultFilePath) {
        for (String result : resultLines) {
            writer.writeLineInFile(resultFilePath, result);
        }
    }

    public List<String> getResultsOfData(List<String> data) {
        List<String> resultLines = new ArrayList<>();
        for (String line : data) {
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
