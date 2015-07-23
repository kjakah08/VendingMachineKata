package com.company;

/**
 * Created by KJA on 7/22/2015.
 */
public class VendingMachine {

    //
    public String insertCoin(String coinInserted) {

        String santizeInputCoin = coinInserted.toLowerCase().trim();
        String output = "";

        if (santizeInputCoin == "penny") {
            output = "Rejected placed in coin return";

        } else if (santizeInputCoin == "nikel" || santizeInputCoin == "dime" || santizeInputCoin == "quarter") {
            switch (santizeInputCoin) {
                case "nikel": output = "Coin Accepted total is: ";
                    break;
                case "dime": output = "Coin Accepted total is: ";
                    break;
                case "quarter": output = "Coin Accepted total is: ";
                    break;
            }
        } else if (santizeInputCoin == "") {
            output = "INSERT COIN";
        } else {
            output = "Rejected placed in coin return";
        }
        return output;
    }

//    // Displays present amount
//    public String () {
//
//    }


}
