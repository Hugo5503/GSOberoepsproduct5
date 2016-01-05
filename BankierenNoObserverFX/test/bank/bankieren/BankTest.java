/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import fontys.util.NumberDoesntExistException;
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
public class BankTest {
    Bank b;
    Klant k1;
    Klant k2;
    Rekening r1;
    Rekening r2;
    
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        b = new Bank("Rabobank");
        k1 = new Klant("Hans", "Eindhoven");
        k2 = new Klant("Hugo", "Veldhoven");
        r1 = new Rekening(100000000, k1, new Money(100, "€"));
        r2 = new Rekening(100000001, k2, new Money(100, "€"));
        b.openRekening("Hans", "Eindhoven");
        b.openRekening("Hugo", "Veldhoven");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        String name = "";
        String city = "Eindhoven";
        Bank instance = b;
        int expResult = -1;
        int result = instance.openRekening(name, city);
        assertEquals("Rekening is aangemaakt zonder naam", expResult, result);
        name = "hans";
        city = "";
        result = instance.openRekening(name, city);
        assertEquals("Rekening is aangemaakt zonder stad", expResult, result);
        name = "Ruud";
        city = "Bakel";
        expResult = 100000002;
        result = instance.openRekening(name, city);
        assertEquals("Rekening is niet aangemaakt", expResult, result);        
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        System.out.println("getRekening");
        int nr = 0;
        Bank instance = b;
        IRekening expResult = null;
        IRekening result = instance.getRekening(nr);
        assertEquals("Niet bestaande rekening is opgehaald", expResult, result);
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int source = 100000000;
        int destination = 100000001;
        Money money = new Money(50, "€");
        Bank instance = b;
        boolean expResult = true;
        boolean result = instance.maakOver(source, destination, money);
        assertEquals("Bedrag is niet overgemaakt", expResult, result);
        money = new Money(100000, "€");
        expResult = false;
        result = instance.maakOver(source, destination, money);
        assertEquals("Bedrag met overschreeden saldo is overgemaakt" + r1.getSaldo().getCents() + r2.getSaldo().getCents(), expResult, result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testMaakOverDubbelesource() throws NumberDoesntExistException {
        b.maakOver(100000000, 100000000, new Money(200, "€"));
    }
    
    @Test(expected = RuntimeException.class)
    public void testMaakOverNegatiefBedrag() throws NumberDoesntExistException {
        b.maakOver(100000000, 100000001, new Money(-200, "€"));
    }
    
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOverNonExcistSource() throws NumberDoesntExistException {
        b.maakOver(1, 100000001, new Money(200, "€"));
    }
    
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOverNonExcistDest() throws NumberDoesntExistException {
        b.maakOver(100000000, 1, new Money(200, "€"));
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Bank instance = b;
        String expResult = "Rabobank";
        String result = instance.getName();
        assertEquals("Naam van bank is niet juist opgehaald", expResult, result);
    }
    
}
