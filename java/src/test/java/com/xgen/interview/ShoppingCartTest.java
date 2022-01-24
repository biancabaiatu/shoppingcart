package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("apple - 1 - €1.00" + '\n', receiptLines[0]);

    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("apple - 2 - €2.00" + '\n', receiptLines[0]);
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] receiptLines = myOut.toString().split("\n");


        assertEquals("banana - 1 - €2.00" + '\n', receiptLines[0]);
        assertEquals("apple - 2 - €2.00" + '\n', receiptLines[1]);
    }

        @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("crisps - 2 - €0.00" + "\n", receiptLines[0]);
    }

        @Test
    public void multipleCarts() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        ShoppingCart sc1 = new ShoppingCart(new Pricer());
        sc1.addItem("banana", 2);
        sc1.addItem("banana", 1);
        sc1.addItem("apple", 3);
        sc1.addItem("banana", 1);
        sc1.printReceipt();

        ShoppingCart sc2 = new ShoppingCart(new Pricer());
        sc2.addItem("apple", 2);
        sc2.addItem("banana", 5);
        sc2.printReceipt();

        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("banana - 4 - €8.00" + "\n", receiptLines[0]);
        assertEquals("apple - 3 - €3.00" + "\n", receiptLines[1]);

        assertEquals("apple - 2 - €2.00" + "\n", receiptLines[3]);
        assertEquals("banana - 5 - €10.00" + "\n", receiptLines[4]);
    }

        @Test
    public void printReceiptGroupedItems() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("banana", 1);
        sc.addItem("banana", 4);
        sc.addItem("apple", 1);
        sc.addItem("apple", 3);
        sc.addItem("banana", 1);
        sc.printReceipt();

        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("banana - 6 - €12.00" + '\n', receiptLines[0]);
        assertEquals("apple - 4 - €4.00" + '\n', receiptLines[1]);
    }

        @Test
    public void checkTotalOfReceipt() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("banana", 4);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] receiptLines = myOut.toString().split("\n");

        assertEquals("RECEIPT TOTAL: €10.00" + '\n', receiptLines[2]);
    }

        @Test
    public void checkLineFormat() {
            final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));

            ShoppingCart sc = new ShoppingCart(new Pricer(), PriceFormat.PRICE_FIRST);

            sc.addItem("apple", 3);
            sc.addItem("apple", 1);
            sc.addItem("banana", 1);

            sc.printReceipt();

            String[] receiptLines = myOut.toString().split("\n");

            assertEquals("€2.00 - banana x 1" + '\n', receiptLines[0]);
            assertEquals("€4.00 - apple x 4" + '\n', receiptLines[1]);

    }

}


