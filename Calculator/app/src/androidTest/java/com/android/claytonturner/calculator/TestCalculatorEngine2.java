package com.android.claytonturner.calculator;

/**
 * Created by Clayton on 2/10/2015.
 */

import com.android.claytonturner.calculator.CalculatorEngine;
import com.android.claytonturner.calculator.Operation;

public class TestCalculatorEngine2
{
    public static void main(String[] args)
    {
        CalculatorEngine ce = new CalculatorEngine(12);
        System.out.println("Initial calculator ...");
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting digits 1, 2, and 3 ...");
        ce.insert('1');
        ce.insert('2');
        ce.insert('3');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming ADD");
        ce.perform(Operation.ADD);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting digits 4, 5, and 6 ...");
        ce.insert('4');
        ce.insert('5');
        ce.insert('6');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming ADD");
        ce.perform(Operation.ADD);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting digits 1, 1, and 0 ...");
        ce.insert('1');
        ce.insert('1');
        ce.insert('0');
        System.out.println(ce);

        System.out.println();
        System.out.println("Performming EQUALS");
        ce.perform(Operation.EQUALS);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting eleven digits and then toggling the sign ...");
        ce.insert('1');
        ce.insert('2');
        ce.insert('3');
        ce.insert('4');
        ce.insert('5');
        ce.insert('6');
        ce.insert('7');
        ce.insert('8');
        ce.insert('9');
        ce.insert('0');
        ce.insert('1');
        ce.toggleSign();
        System.out.println(ce);
        System.out.println();

        System.out.println("Toggling the sign once again ...");
        ce.toggleSign();
        System.out.println(ce);
        System.out.println();

        System.out.println("Clearing the calculator ...");
        ce.clear();
        System.out.println(ce);
        System.out.println();

        System.out.println("Attempting to insert 13 '9' digits ...");
        ce.clear();
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        ce.insert('9');
        System.out.println(ce);
        System.out.println();

        System.out.println("Clearing the calculator ...");
        ce.clear();
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting 123456.789 ...");
        ce.insert('1');
        ce.insert('2');
        ce.insert('3');
        ce.insert('4');
        ce.insert('5');
        ce.insert('6');
        ce.insert('.');
        ce.insert('7');
        ce.insert('8');
        ce.insert('9');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming MULTIPLY");
        ce.perform(Operation.MULTIPLY);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting digit 2 ...");
        ce.insert('2');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming EQUALS");
        ce.perform(Operation.EQUALS);
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming ADD");
        ce.perform(Operation.ADD);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting .1 ...");
        ce.insert('.');
        ce.insert('1');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming EQUALS");
        ce.perform(Operation.EQUALS);
        System.out.println(ce);
        System.out.println();

        System.out.println("Clearing the calculator ...");
        ce.clear();
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting 1 ...");
        ce.insert('1');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming DIVIDE");
        ce.perform(Operation.DIVIDE);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting 3 ...");
        ce.insert('3');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming EQUALS");
        ce.perform(Operation.EQUALS);
        System.out.println(ce);
        System.out.println();

        System.out.println("Clearing the calculator ...");
        ce.clear();
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting 2 ...");
        ce.insert('2');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming DIVIDE");
        ce.perform(Operation.DIVIDE);
        System.out.println(ce);
        System.out.println();

        System.out.println("Inserting 3 ...");
        ce.insert('3');
        System.out.println(ce);
        System.out.println();

        System.out.println("Performming EQUALS");
        ce.perform(Operation.EQUALS);
        System.out.println(ce);
        System.out.println();
    }
}
