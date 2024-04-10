package fr.hetic.operations;

public class AdditionStrategy implements CalculateStrategy{
    @Override
    public Integer calculate(Integer number_1, Integer number_2) {
        return number_1 + number_2;
    }
}
