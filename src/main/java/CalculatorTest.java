import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private Calculator calculator;
    public void setup(){
        calculator = new Calculator;
    }

    @Test
    public void testAddition(){
        assertEquals(5.0, calculator.evaluateMathExpression("2+3"), 0.0001);
    }

    @Test
    public void testSubtraction(){
        assertEquals(1.0, calculator.evaluateMathExpression("5-4"), 0.0001);
    }

    @Test
    public void testMultiplication(){
        assertEquals(20.0, calculator.evaluateMathExpression("4*5"), 0.0001);
    }

    @Test
    public void testMixedOperations(){
        assertEquals(14.0, calculator.evaluateMathExpression("2+3*4"), 0.0001);
    }

    @Test
    public void testDecimalNumbers(){
        assertEquals(5.9, calculator.evaluateMathExpression("2.5+3.4"), 0.0001);
    }

    @Test
    public void testWithSpaces(){
        assertEquals(7.0, calculator.evaluateMathExpression("3+ 4"), 0.0001);
    }

}
