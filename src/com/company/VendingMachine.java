package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
    public static Double currencyInMachine = 0.00;
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
                // Decrement the amount from total
                currencyInMachine -= returnItemPrice(productSelected);
                System.out.print("Amount in machine after purchasing " + productSelected + " is " + String.valueOf(currencyInMachine));

                output = "THANK YOU," + makeChange(currencyInMachine);

            } else { // No more product:
                output = "SOLD OUT";
            }

        } else {   // NOT enough money
            output = "INSERT COINS";

        }

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
    public String makeChange(Double moneyInMachine) {

        // 0: Quarters, 1: Dimes, 2: Nickels
        Integer[] numberEachCoin = {0, 0, 0};

        String[] coinOutputString = {"", "", ""};

        output = "ok";


        // Return largest to smallest currency, quarters, dimes, nickels...
        while (round(moneyInMachine,2) > 0.00) {

            if (moneyInMachine - 0.25 >= 0.00) {
                // Add a quarter:
                numberEachCoin[0] += 1;
                moneyInMachine -= 0.25;

            } else {

                if (round(moneyInMachine,2) - 0.10 >= 0.00) {

                    // Add a dime
                    numberEachCoin[1] += 1;
                    moneyInMachine -= 0.10;

                } else {
                    // Add a nickel
                    numberEachCoin[2] += 1;
                    moneyInMachine -= 0.05;
                }

            }

        }

        // clean output:
        coinOutputString[0] = (numberEachCoin[0] == 0) ? "" : numberEachCoin[0].toString() + " quarter";
        coinOutputString[1] = (numberEachCoin[1] == 0) ? "" : numberEachCoin[1].toString() + " dime";
        coinOutputString[2] = (numberEachCoin[2] == 0) ? "" : numberEachCoin[2].toString() + " nickel";

        //output = (numberEachCoin[0] + numberEachCoin[1] + numberEachCoin[2] == 0) ? "" : "Returned: ";

        return " Returned: " + coinOutputString[0] + coinOutputString[1] + coinOutputString[2];

    }

    // Setting total money input back (will need to calculate change in the future)
    public void resetMachineCoinTotal() {
        currencyInMachine = 0.00;
    }

    // Helper rounding:
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
