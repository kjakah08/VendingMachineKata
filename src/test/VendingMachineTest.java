package test;

import com.company.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static junit.framework.Assert.assertEquals;

/**
 * Created by KJA on 7/22/2015.
 */
public class VendingMachineTest {

    VendingMachine vendingMachine = new VendingMachine();

    // INPUT TESTS:
    @Test
    public void doNotAcceptPennies() {
        assertEquals("Rejected placed in coin return", vendingMachine.insertCoin("penny"));
    }

    @Test
    public void acceptOnlyAmerican() {
        assertEquals("Rejected placed in coin return", vendingMachine.insertCoin("other coins"));
    }

    @Test
    public void noCoinsDisplayInsertCoin() {
        assertEquals("INSERT COIN", vendingMachine.insertCoin(""));
    }

    // Input Calculation tests:
    @Test
    public void shouldShow40CentsInMachine() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("dime");
        vendingMachine.insertCoin("nickel");
        assertEquals("Coin Accepted total is: 0.40", vendingMachine.insertCoin("quarter"));
        vendingMachine.resetMachineCoinTotal();

    }

    @Test
    public void shouldShow25CentsInMachine() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("dime");
        vendingMachine.insertCoin("nickel");
        assertEquals("Coin Accepted total is: 0.25", vendingMachine.insertCoin("dime"));
        vendingMachine.resetMachineCoinTotal();
    }

    // Purchasing tests:
    @Test
    public void exactAmountReturnsItemNoChange() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("THANK YOU, Returned: ", vendingMachine.selectProduct("chips"));
        vendingMachine.resetMachineCoinTotal();
    }

    @Test
    public void tooMuchMoneyReturnsExactChangeNickel() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("nickel");

        assertEquals("THANK YOU, Returned: 1 nickel", vendingMachine.selectProduct("chips"));
        vendingMachine.resetMachineCoinTotal();
    }

    @Test
    public void tooMuchMoneyChipsReturnsExactChangeQuarter() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("THANK YOU, Returned: 1 quarter", vendingMachine.selectProduct("chips"));
        vendingMachine.resetMachineCoinTotal();
    }

    @Test
    public void tooMuchMoneyCandyReturnsExactChangeQuarter() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("THANK YOU, Returned: 1 dime", vendingMachine.selectProduct("candy"));
        vendingMachine.resetMachineCoinTotal();
    }

    @Test
    public void notEnoughMoney() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("INSERT COINS", vendingMachine.selectProduct("candy"));
        vendingMachine.resetMachineCoinTotal();
    }

    // Inventory Tests:
    @Test
    public void itemIsSoldOut() {

        String[] productChoices = {"chips", "cola", "candy"};

        for (int i = 0; i < productChoices.length; i++) {
            for (int j = 0; j <= 50; j++) {
                vendingMachine.insertCoin("quarter");
            }

            vendingMachine.selectProduct(productChoices[i]);
            vendingMachine.selectProduct(productChoices[i]);

            assertEquals("SOLD OUT", vendingMachine.selectProduct(productChoices[i]));

            vendingMachine.resetMachineCoinTotal();
        }
    }

    @Test
    public void itemIsSoldOutSelectAnotherNotEnough() {

        for (int i = 0; i < 6; i++) {
            vendingMachine.insertCoin("quarter");
        }

        vendingMachine.selectProduct("chips");
        vendingMachine.selectProduct("chips");

        assertEquals("SOLD OUT", vendingMachine.selectProduct("chips"));
        assertEquals("INSERT COINS", vendingMachine.selectProduct("cola"));

        vendingMachine.resetMachineCoinTotal();
    }

    @Test
    public void itemIsSoldOutSelectAnotherChange() {

        for (int i = 0; i < 7; i++) {
            vendingMachine.insertCoin("quarter");
        }

        vendingMachine.selectProduct("chips");
        vendingMachine.selectProduct("chips");

        assertEquals("SOLD OUT", vendingMachine.selectProduct("chips"));
        assertEquals("THANK YOU, Returned: 1 dime", vendingMachine.selectProduct("candy"));

        vendingMachine.resetMachineCoinTotal();
    }

    // Returning the
    @Test
    public void noPurchaseCoinsReturned() {

        for (int i = 0; i < 7; i++) {
            vendingMachine.insertCoin("quarter");
        }

        assertEquals("INSERT COINS, Returned: 7 quarter", vendingMachine.returnCoins());

        vendingMachine.resetMachineCoinTotal();
    }

}
