package fr.hetic.models;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Line {
    private final List<String> SUPPORTED_OPERATORS = Arrays.asList("+", "-", "*");
    public Integer number_1 = null;
    public Integer number_2 = null;
    public String operator = null;
    public Boolean isValid = false;

    public Line(Integer number_1, Integer number_2, String operator) {
        String lineInput = String.format("%d %d %s", number_1, number_2, operator);
        if(verifyLineInput(lineInput)) {
            this.number_1 = number_1;
            this.number_2 = number_2;
            this.operator = operator;
            this.isValid = true;
        }
    }

    private Boolean verifyLineInput(String lineInput) {
        try {
            String[] args = lineInput.split(StringUtils.SPACE);
            if (args.length != 3) {
                throw new Exception("Number of args incorrect");
            }

            if (!SUPPORTED_OPERATORS.contains(args[2])) {
                throw new Exception("Operator not supported");
            }

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public String getArgumentsOfLine() {
        return String.format("%d %d %s", number_1, number_2, operator);
    }

}
