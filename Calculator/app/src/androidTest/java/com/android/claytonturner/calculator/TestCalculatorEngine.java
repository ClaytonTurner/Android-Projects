package com.android.claytonturner.calculator;

/**
 * Created by Clayton on 2/10/2015.
 */
import java.util.Scanner;

import com.android.claytonturner.calculator.CalculatorEngine;
import com.android.claytonturner.calculator.Operation;


public class TestCalculatorEngine
{
    public static void main(String[] args)
    {
        char operation;

        Scanner sc = new Scanner(System.in);
        CalculatorEngine ce = new CalculatorEngine(12);

        putMenu();

        do
        {
            System.out.print("Enter choice:  ");
            String choice = sc.next();
            operation = choice.charAt(0);

            switch(operation)
            {
                case '+':
                    ce.perform(Operation.ADD);
                    break;
                case '-':
                    ce.perform(Operation.SUBTRACT);
                    break;
                case '*':
                    ce.perform(Operation.MULTIPLY);
                    break;
                case '/':
                    ce.perform(Operation.DIVIDE);
                    break;
                case '=':
                    ce.perform(Operation.EQUALS);
                    break;
                case 'i':
                case 'I':
                    System.out.print("Enter digits:  ");
                    String digits = sc.next();
                    for (int i = 0;  i < digits.length();  ++i)
                        ce.insert(digits.charAt(i));
                    break;
                case 'c':
                case 'C':
                    ce.clear();
                    break;
                case 'e':
                case 'E':
                    ce.clearEntry();
                    break;
                case 'n':
                case 'N':
                    ce.toggleSign();
                    break;
                case '?':
                    putMenu();
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("*** INVALID OPERATION ***" );
                    putMenu();
            }
            System.out.println("Display:  " + ce.getDisplay());
            System.out.println();

        }
        while(operation != 'q');
    }


    private static void putMenu()
    {
        System.out.println("Choose operations as follows:\n");
        System.out.println("(I) Insert Value (digits and '.')");
        System.out.println("(+) Add        (-) Subtract");
        System.out.println("(*) Multiply   (/) Divide");
        System.out.println("(N) Negate     (=) Equals");
        System.out.println("(c) Clear      (E) Clear Entry");
        System.out.println("(?) Help       (q) Quit");
    }

}