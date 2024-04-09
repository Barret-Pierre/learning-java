package fr.hetic;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Integer number_1 = Integer.parseInt(args[0]);
        Integer number_2 = Integer.parseInt(args[1]);
        String operator = args[2];

        switch(operator) {
            case "+":
                System.out.println("The result is " + (number_1 + number_2));
                break;
            case "-":
                System.out.println("The result is " + (number_1 - number_2));
                break;
            case "*":
                System.out.println("The result is " + (number_1 * number_2));
                break;
            default:
                throw new Exception("Operator not allowed");
        }
    }
}
