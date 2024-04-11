package fr.hetic;

import fr.hetic.models.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {
    @Test
    public void testValidInput() {
        Line line = new Line(10, 20,  "+");
        assertTrue(line.isValid);
        assertEquals(Integer.valueOf(10), line.number_1);
        assertEquals(Integer.valueOf(20), line.number_2);
        assertEquals("+", line.operator);
    }

    @Test
    public void testInvalidInput_InvalidOperator() {
        Line line = new Line(10, 20, "%");
        assertFalse(line.isValid);
    }
}
