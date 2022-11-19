package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaulatorTest {

    @Test
    void testingBasicComputing(){
        // Testing computing values of correct input
        String a1 = "( sqrt ( -2 / -2 ) )";
        String a2 = "( 1 / ( 2 / 3 ) )";
        String a3 = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Basic computing",
                () -> assertEquals(s.evaluate(a1) , 1),
                () -> assertEquals(s.evaluate(a2) , 1.5),
                () -> assertEquals(s.evaluate(a3), 101)

        );
    }
    @Test
    void testingBrackets(){
        // Testing incorrect input - parentheses error
        String a1 = "( 1 + ( 2 + 3 ) ) ) - 1 + 2 + ( 2 + ( 3 + 4 )";
        String a2 = "( 1.1 * 5 + ( sqrt ( 32 / 2 ) ) )";
        String a3 = "( 3 * 2 + 2 )";
        String a4 = "( sqrt 4 )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Parentheses",
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a1);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a2);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a3);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a4);})
        );
    }
    @Test
    void testNegativeValues(){
        // Testing working with negative numbers
        String a1 = "( -2 + 5 )";
        String a2 = "( -2 / -5 )";
        String a3 = "( 4 + ( -3 * 5 ) )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Negative computing",
                () -> assertEquals(s.evaluate(a1) , 3),
                () -> assertEquals(s.evaluate(a2) , 0.4),
                () -> assertEquals(s.evaluate(a3), -11)
        );
    }
    @Test
    void testDivideWithZero(){
        // Testing whether division by zero is allowed under any circumstances

        String a1 = "( 5 / 0 )";
        String a2 = "( 5 / ( 4 - 4 ) )";
        String a3 = "( (sqrt 4 ) / ( ( sqrt 1 ) - 1 ) )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Divide by zero",
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a1);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a2);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a3);})
        );
    }
    @Test
    void testNegativeRoot(){
        // Testing if sqrt has a negative argument

        String a1 = "( 6 * sqrt ( ( 3 - 5 ) ) )";
        String a2 = "( sqrt ( 3 - ( 6 * 2 ) ) )";
        String a3 = "( sqrt ( ( sqrt ( 4 ) ) - 5 ) )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Negative argument for sqrt",
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a1);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a2);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a3);})
        );
    }
    @Test
    void testEmptySpaces(){
        // Testing if input has correct form
        String a1 = "()";
        String a2 = "(sqrt ( 4 ) )";
        String a3 = "( 3 + ( 2 * * 2 ) )";
        ExpressionEvaluator s = new ExpressionEvaluator();
        assertAll(
                "Negative argument for sqrt",
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a1);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a2);}),
                () -> assertThrows(RuntimeException.class, () -> {s.evaluate(a3);})
        );
    }



}
