/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.*;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugoL
 */
public class BankiersessieTest {
    
    Bank banktest;
    Bankiersessie bankiersessietest;
    Bankiersessie bankiersessietest2;
    int rekeningnummer1;
    int rekeningnummer2;
    
    
    public BankiersessieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws RemoteException {
        banktest = new Bank("Rabobank");      
        rekeningnummer1 = banktest.openRekening("Hans", "Eindhoven");
        rekeningnummer2 = banktest.openRekening("Hugo", "Veldhoven");
        bankiersessietest = new Bankiersessie(rekeningnummer1,banktest);
        bankiersessietest2 = new Bankiersessie(rekeningnummer2,banktest);
    }
        
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of isGeldig method, of class Bankiersessie.
     */
    @Test
    public void testIsGeldig() {
        System.out.println("isGeldig");
        Bankiersessie instance = bankiersessietest;
        boolean expResult = true;
        boolean result = instance.isGeldig();
        assertEquals("Geldige instantie is niet aangemaakt.",expResult, result);
    }

    /**
     * Test of maakOver method, of class Bankiersessie.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int bestemming = rekeningnummer2;
        Money bedrag = new Money(100, "€");
        Bankiersessie instance = bankiersessietest;
        boolean expResult = true;
        boolean result = instance.maakOver(bestemming, bedrag);
        assertEquals("Geld is niet overgemaakt",expResult, result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testMaakOverZelfdeBestemming() throws Exception {
        System.out.println("maakOverzelfdebestemming");
        int bestemming = rekeningnummer1;
        Money bedrag = new Money(100, "€");
        Bankiersessie instance = bankiersessietest;
        boolean expResult = true;
        boolean result = instance.maakOver(bestemming, bedrag);
        assertEquals("Geld is overgemaakt",expResult, result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testMaakOverNegatiefBedrag() throws RuntimeException, NumberDoesntExistException, InvalidSessionException, RemoteException {
        System.out.println("maakOvernegatiefbedrag");
        int bestemming = rekeningnummer2;
        Money bedrag = new Money(-100, "€");
        Bankiersessie instance = bankiersessietest;
        boolean expResult = false;
        boolean result = instance.maakOver(bestemming, bedrag);
        assertEquals("Geld is niet overgemaakt",expResult, result);
    }
    
    /**
     * Test of getRekening method, of class Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception {
        System.out.println("getRekening");
        Bankiersessie instance = bankiersessietest;
        IRekening expResult = bankiersessietest.getRekening();
        IRekening result = instance.getRekening();
        assertEquals("Rekeningen komen niet overeen.",expResult, result);
    }
    
    /**
     * Test of logUit method, of class Bankiersessie.
     */
    @Test
    public void testLogUit() throws Exception {
        System.out.println("logUit");
        Bankiersessie instance = bankiersessietest;
        instance.logUit();
    }
    
}
