package fr.hetic;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Line {
    private final List<String> SUPPORTED_OPERATORS = Arrays.asList("+", "-", "*");
    public Integer number_1 = null;
    public Integer number_2 = null;
    public String operator = null;
    public Boolean isValid = false;

    public Line(String lineInput) {
        if(verifyLineInput(lineInput)) {
            this.number_1 = Integer.parseInt(lineInput.split(StringUtils.SPACE)[0]);
            this.number_2 = Integer.parseInt(lineInput.split(StringUtils.SPACE)[1]);
            this.operator = lineInput.split(StringUtils.SPACE)[2];
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

            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
