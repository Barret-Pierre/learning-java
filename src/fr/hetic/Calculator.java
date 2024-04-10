package fr.hetic;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    private static final List<String> SUPPORTED_OPERATORS = Arrays.asList("+", "-", "*");

    public Integer calculateResult(Integer number_1, Integer number_2, String operator) {
        return switch (operator) {
            case "+" -> number_1 + number_2;
            case "-" -> number_1 - number_2;
            case "*" -> number_1 * number_2;
            default -> null;
        };
    }
}
