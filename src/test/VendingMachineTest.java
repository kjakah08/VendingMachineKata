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

        assertEquals("THANK YOU", vendingMachine.selectProduct("chips"));
    }

    @Test
          public void tooMuchMoneyReturnsExactChangeNickel() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("nickel");

        assertEquals("THANK YOU, returned 1 nickel", vendingMachine.selectProduct("chips"));
        assertEquals(0.00, vendingMachine.currencyInMachine);
    }

    @Test
    public void tooMuchMoneyChipsReturnsExactChangeQuarter() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("THANK YOU, returned 1 quarter", vendingMachine.selectProduct("chips"));
        assertEquals(0.00, vendingMachine.currencyInMachine);
    }

    @Test
    public void tooMuchMoneyCandyReturnsExactChangeQuarter() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("THANK YOU, returned 1 nickel", vendingMachine.selectProduct("candy"));
        assertEquals(0.00, vendingMachine.currencyInMachine);
    }

    @Test
    public void notEnoughMoney() {
        vendingMachine.insertCoin("penny");
        vendingMachine.insertCoin("quarter");
        vendingMachine.insertCoin("quarter");

        assertEquals("INSERT COINS", vendingMachine.selectProduct("candy"));
    }

    // Inventory Tests:


}
