package test;

import com.company.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by KJA on 7/22/2015.
 */
public class VendingMachineTest {

    VendingMachine vendingMachine = new VendingMachine();

    @Test
    public void doNotAceptPennies() {
        assertEquals("Rejected placed in coin return", vendingMachine.insertCoin("penny"));
    }

    @Test
    public void acceptOnlyAmerican() {
        assertEquals("Rejected placed in coin return", vendingMachine.insertCoin("other coins"));
    }

    @Test
    public void acceptNikelDisplayTotal() {
        assertEquals("Coin Accepted total is: ", vendingMachine.insertCoin("nikel"));
    }

    @Test
    public void acceptDimeDisplayTotal() {
        assertEquals("Coin Accepted total is: ", vendingMachine.insertCoin("dime"));
    }

    @Test
    public void acceptQuarterDisplayTotal() {
        assertEquals("Coin Accepted total is: ", vendingMachine.insertCoin("quarter"));
    }

    @Test
    public void noCoindsDisplayInsertCoin() {
        assertEquals("INSERT COIN", vendingMachine.insertCoin(""));
    }

}
