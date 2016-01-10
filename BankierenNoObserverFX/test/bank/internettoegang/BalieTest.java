/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruud
 */
public class BalieTest {
    Bank b;
    Balie bal;  
    
    public BalieTest() {              
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            b = new Bank("Rabobank");        
            bal = new Balie(b);
        } catch (RemoteException ex) {
            Logger.getLogger(BalieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() throws RemoteException {
        System.out.println("openRekening");
        String naam = "Hans";
        String plaats = "Eindhoven";
        String wachtwoord = "kaas123";
        Balie instance = bal;
        String expResult = "8";
        int expResultLength = 8;
        int resultLength = instance.openRekening(naam, plaats, wachtwoord).length();
        String result = null;
        assertEquals(expResultLength, resultLength);
        naam = "";
        plaats = "Eindhoven";
        wachtwoord = "kaas123";
        expResult = null;
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals("rekening zonder naam is aangemaakt",expResult, result);
        naam = "Hans";
        plaats = "";
        wachtwoord = "kaas123";
        expResult = null;
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals("rekening zonder plaats is aangemaakt",expResult, result);
        naam = "Hans";
        plaats = "Eindhoven";
        wachtwoord = "kaa";
        expResult = null;
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals("rekening met te kort wachtwoord is aangemaakt",expResult, result);
        naam = "Hans";
        plaats = "Eindhoven";
        wachtwoord = "kaas12345";
        expResult = null;
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals("rekening met te lang wachtwoord is aangemaakt",expResult, result);
        
        
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception {
        System.out.println("logIn");
        String accountnaam = bal.openRekening("Hans", "Eindhoven", "kaas123");
        String wachtwoord = "kaas123";
        Balie instance = bal;
        IBankiersessie expResult = new Bankiersessie(100000000, b);
        IBankiersessie result = instance.logIn(accountnaam, wachtwoord);
        assertEquals("Kan bankiersessie niet openen",expResult.getRekening(), result.getRekening());
        accountnaam = null;
        wachtwoord = "kaas123";
        expResult = null;
        result = instance.logIn(accountnaam, wachtwoord);
        assertEquals("Banksessie zonder account is geopend",expResult, result);
        accountnaam = bal.openRekening("Hans", "Eindhoven", "kaas123");
        wachtwoord = "";
        expResult = null;
        result = instance.logIn(accountnaam, wachtwoord);
        assertEquals("Banksessie zonder wachtwoord is geopend",expResult, result);        
    }
    
}
