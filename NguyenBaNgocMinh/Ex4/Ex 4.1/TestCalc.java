import org.junit.Test;
import static org.junit.Assert.*;

public class Test {
    Calc calc = new Calc();
    private int a = 1;
    private int b = 1;

    @Test
    public void testAdd() {
        assertEquals(calc.add(a, b), 2);
    }

    @Test
    public void testMinus() {
        assertEquals(calc.minus(a, b), 0);
    }

    @Test
    public void testMultiply() {
        assertEquals(calc.multiply(a, b), 1);
    }

    @Test
    public void testDivide() {
        assertEquals(calc.divide(a, b), 1);
    }
}