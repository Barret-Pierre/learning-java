package fr.hetic;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Integer number_1 = Integer.parseInt(args[0]);
        Integer number_2 = Integer.parseInt(args[1]);
        String operator = args[2];

        showResult(calculate(number_1, number_2, operator));

    }

    public static Integer calculate(Integer number_1, Integer number_2, String operator) throws Exception {
        return switch (operator) {
            case "+" -> number_1 + number_2;
            case "-" -> number_1 - number_2;
            case "*" -> number_1 * number_2;
            default -> throw new Exception("Operator not allowed");
        };
    }

    public static void showResult(Integer result) {
        System.out.println("The result is " + result);
    }
}
