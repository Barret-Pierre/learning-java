package fr.hetic.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionStrategyTest {

    @Test
    public void testCalculate() {
        // Arrange
        AdditionStrategy additionStrategy = new AdditionStrategy();

        // Act
        int result = additionStrategy.calculate(3, 4);

        // Assert
        assertEquals(7, result, "3 + 4 should equal 7");
}
}
