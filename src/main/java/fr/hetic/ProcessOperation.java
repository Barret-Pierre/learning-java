package fr.hetic;

import fr.hetic.models.Line;
import fr.hetic.writers.WriterStrategy;
import org.apache.commons.lang3.StringUtils;

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
            System.out.println(result);
            resultLines.add(result);
        }
        return resultLines;
    }

    public List<Line> mapContentFileLines(List<String> contentFile) {
        List<Line> lines = new ArrayList<>();
        for (String contentLine : contentFile) {
            if(Line.verifyLineInput(contentLine)) {
                int number_1 = Integer.parseInt(contentLine.split(StringUtils.SPACE)[0]);
                int number_2 = Integer.parseInt(contentLine.split(StringUtils.SPACE)[1]);
                String operator = contentLine.split(StringUtils.SPACE)[2];
                Line line = new Line(number_1, number_2, operator);
                lines.add(line);
            } else  {
                Line line = new Line(null, null, null);
                lines.add(line);
            }
        }
        System.out.println(lines);
        System.out.println(lines);
        return lines;
    }
}
