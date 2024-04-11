package fr.hetic;

import fr.hetic.models.Line;
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

    public List<String> getResultsOfData(List<Line> data) {
        List<String> resultLines = new ArrayList<>();
        for (Line line : data) {
            String result = "ERROR\n";
            if (line.isValid) {
                result = calculator.calculateResult(line.number_1, line.number_2, line.operator) + "\n";
            }
            resultLines.add(result);
        }
        return resultLines;
    }

}
