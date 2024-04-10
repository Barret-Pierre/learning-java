package fr.hetic.operations;

@FunctionalInterface
public interface CalculateStrategy {
    Integer calculate(Integer number_1, Integer number_2);
}
