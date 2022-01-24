package com.xgen.interview;

import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    Map<String, Integer> contents = new HashMap<>();
    Pricer pricer;
    PriceFormat priceFormat;


    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
        this.setPriceFormat(PriceFormat.PRICE_AFTER);
    }

    public ShoppingCart(Pricer pricer, PriceFormat priceFormat) {
        this.pricer = pricer;
        this.setPriceFormat(priceFormat);
    }

    /**
     * Allows setting the format of the receipt line output
     * @param priceFormat Integer that matches one of the pre-defined constants
     */
    private void setPriceFormat(PriceFormat priceFormat) {
        if (Objects.equals(priceFormat, PriceFormat.PRICE_FIRST)) {
            this.priceFormat = PriceFormat.PRICE_FIRST;
        } else {
            this.priceFormat = PriceFormat.PRICE_AFTER;
        }
    }

    /**
     * Allows printing a receipt line in the desired format
     * @param item Name of the item to be printed
     * @param itemAmount Amount of the item in the cart
     * @param priceFloat Price in floating point format
     * @return A formatted receipt line
     */
    private String getReceiptLineFormat(String item, Integer itemAmount, Float priceFloat) {
        String priceString = String.format("€%.2f", priceFloat);
        String line;
        if (this.priceFormat == PriceFormat.PRICE_FIRST) {
            line = priceString + " - " + item + " x " + itemAmount;
        } else {
            line = item + " - " + itemAmount + " - " + priceString;
        }
        return line;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {

        float totalReceipt = (float) 0;

        for (String item : contents.keySet()) {
            Integer itemAmount = contents.get(item);

            Float priceFloat = print(item, itemAmount);

            totalReceipt += priceFloat;
        }

        System.out.println("RECEIPT TOTAL: " + String.format("€%.2f", totalReceipt));

    }

    /**
     * Allows printing of a line in the receipt given the item and the amount
     * @param item Name of the item in the shopping cart
     * @param itemAmount Number of items in the shopping cart
     * @return Float value that represents the price of the products as floating point
     */
    private Float print(String item, Integer itemAmount) {
        int pricePerAmount = pricer.getPrice(item) * itemAmount;
        Float priceFloat = (float) pricePerAmount / 100;

        System.out.println(getReceiptLineFormat(item, itemAmount, priceFloat));

        return priceFloat;
    }

}
