package fr.hetic;

import fr.hetic.operations.AdditionStrategy;
import fr.hetic.operations.CalculateStrategy;
import fr.hetic.operations.MultiplicationStrategy;
import fr.hetic.operations.SubtractionStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Calculator {
    private static final Map<String, CalculateStrategy> STRATEGY_MAP = Map.of(
            "+", new AdditionStrategy(),
            "-", new SubtractionStrategy(),
            "*", new MultiplicationStrategy()
    );

    public Integer calculateResult(Integer number_1, Integer number_2, String operator) {
        CalculateStrategy strategy = STRATEGY_MAP.get(operator);
        return strategy.calculate(number_1, number_2);
    }
}
