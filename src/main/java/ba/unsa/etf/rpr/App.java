package ba.unsa.etf.rpr;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String a = args[0];
        try {
            ExpressionEvaluator s = new ExpressionEvaluator();
            System.out.println(s.evaluate(a));
        }catch(RuntimeException err){
            System.out.println("Izuzetak: "+ err.getMessage());

        }
    }
}
