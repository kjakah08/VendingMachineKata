package com.company;

import java.text.DecimalFormat;

/**
 * Created by KJA on 7/22/2015.
 */
public class VendingMachine {

    // IdealizeInventory as two of each: cola for $1.00, chips for $0.50, and candy for $0.65
    // To allow simple updating of prices
    Double colaPrice = 1.00;
    Double candyPrice = 0.65;
    Double chipPrice = 0.50;

    Integer colaStock = 2;
    Integer candyStock = 2;
    Integer chipStock = 2;

    Integer[] inventoryArray = {colaStock, candyStock, chipStock};

    DecimalFormat df = new DecimalFormat("0.00");
    public static double currencyInMachine = 0.00;
    String output = "";

    public String insertCoin(String coinInserted) {

        String sanitizeInputCoin = coinInserted.toLowerCase().trim();

        if (sanitizeInputCoin == "penny") {
            output = "Rejected placed in coin return";

        } else if (sanitizeInputCoin == "nickel" || sanitizeInputCoin == "dime" || sanitizeInputCoin == "quarter") {
            switch (sanitizeInputCoin) {
                case "nickel":
                    currencyInMachine = currencyInMachine + 0.05;
                    break;
                case "dime":
                    currencyInMachine = currencyInMachine + 0.10;
                    break;
                case "quarter":
                    currencyInMachine = currencyInMachine + 0.25;
                    break;
            }
            output = "Coin Accepted total is: " + String.valueOf(df.format(currencyInMachine));

        } else if (sanitizeInputCoin == "") {
            output = "INSERT COIN";
        } else {
            output = "Rejected placed in coin return";
        }
        return output;
    }

    // Selection
    public String selectProduct(String productSelected) {
        // 3 cases:
        // Checking money:
        if (checkPrice(productSelected) == 1) {

            // Checking inventory, making sale, and sending back change if passed:
            if (checkInventoryLevel(productSelected) == 1) {
                output = "THANK YOU";

            } else { // No more product:
                output = "SOLD OUT";
            }


        } else {   // NOT enough money
            output = "PRICE: " + returnItemPrice(productSelected).toString();

        }

        return output;
    }


    public String checkDisplay() {
        String output = null;


        return output;
    }


    // Return item price:
    public Double returnItemPrice(String productSelected) {
        Double itemPrice = 0.00;

        switch (productSelected) {
            case "cola":
                itemPrice = colaPrice;
                break;
            case "candy":
                itemPrice = candyPrice;
                break;
            case "chips":
                itemPrice = chipPrice;
                break;
        }

        return itemPrice;
    }


    // checking price against money in the machine
    public int checkPrice(String productSelected) {
        Integer priceCheckTest = 0; // If 0 than failed, 1 then passed:
        Double itemPrice = -0.01;

        switch (productSelected) {
            case "cola":
                itemPrice = colaPrice;
                break;
            case "candy":
                itemPrice = candyPrice;
                break;
            case "chips":
                itemPrice = chipPrice;
                break;
        }

        if ((currencyInMachine - itemPrice) >= 0.00) {
            priceCheckTest = 1;
        }

        return priceCheckTest;
    }


    // checking inventory against users requests
    public int checkInventoryLevel(String productSelected) {
        Integer inventoryCheckTest = 0; // If 0 than failed, 1 then passed:
        Integer inventoryArrayPlaceSelection = 0;

        switch (productSelected) {
            case "cola":
                inventoryArrayPlaceSelection = 0;
                break;
            case "candy":
                inventoryArrayPlaceSelection = 1;
                break;
            case "chips":
                inventoryArrayPlaceSelection = 2;
                break;
        }

        if (inventoryArray[inventoryArrayPlaceSelection] != 0) {

            // Pass test and remove one value from inventory level:
            inventoryCheckTest = 1;
            inventoryArray[inventoryArrayPlaceSelection] -= 1;
        }

        return inventoryCheckTest;
    }


    // Holder method for change
    public String makeChange() {

        return "0";
    }

    // Setting total money input back (will need to calculate change in the future)
    public void resetMachineCoinTotal() {
        currencyInMachine = 0.00;
    }

}
