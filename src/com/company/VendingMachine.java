package com.company;

import java.text.DecimalFormat;

/**
 * Created by KJA on 7/22/2015.
 */
public class VendingMachine {

    DecimalFormat df = new DecimalFormat("0.00");
    public static double currencyInMachine = 0.00;

    public String insertCoin(String coinInserted) {

        String sanitizeInputCoin = coinInserted.toLowerCase().trim();
        String output = "";

        if (sanitizeInputCoin == "penny") {
            output = "Rejected placed in coin return";

        } else if (sanitizeInputCoin == "nikel" || sanitizeInputCoin == "dime" || sanitizeInputCoin == "quarter") {
            switch (sanitizeInputCoin) {
                case "nikel":
                    currencyInMachine = currencyInMachine + 0.05;
                    output = "Coin Accepted total is: " + String.valueOf(df.format(currencyInMachine));
                    break;
                case "dime":
                    currencyInMachine = currencyInMachine + 0.10;
                    output = "Coin Accepted total is: " + String.valueOf(df.format(currencyInMachine));

                    break;
                case "quarter":
                    currencyInMachine = currencyInMachine + 0.25;
                    output = "Coin Accepted total is: " + String.valueOf(df.format(currencyInMachine));
                    break;
            }
        } else if (sanitizeInputCoin == "") {
            output = "INSERT COIN";
        } else {
            output = "Rejected placed in coin return";
        }
        return output;
    }

    public void resetMachineCoinTotal() {
        currencyInMachine = 0.00;
    }

//    // Displays present amount
//    public String () {
//
//    }


}
