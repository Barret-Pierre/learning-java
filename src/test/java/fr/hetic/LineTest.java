package fr.hetic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {
    @Test
    public void testValidInput() {
        Line line = new Line("10 20 +");
        assertTrue(line.isValid);
        assertEquals(Integer.valueOf(10), line.number_1);
        assertEquals(Integer.valueOf(20), line.number_2);
        assertEquals("+", line.operator);
    }

    @Test
    public void testInvalidInput_InvalidOperator() {
        Line line = new Line("10 20 %");
        assertFalse(line.isValid);
    }

    @Test
    public void testInvalidInput_NotEnoughArgs() {
        Line line = new Line("10 20");
        assertFalse(line.isValid);
    }

    @Test
    public void testInvalidInput_NonIntegerArgs() {
        Line line = new Line("10 abc +");
        assertFalse(line.isValid);
    }


}
