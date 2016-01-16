/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.server;

import bank.bankieren.Money;
import bank.internettoegang.IBalie;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruud
 */
public class BankCentrale extends UnicastRemoteObject implements IBankCentrale {

    private int nextRekeningNummer = 100000000;
    private final HashMap<Integer, String> rekeningNummers;

    public BankCentrale() throws RemoteException {
        rekeningNummers = new HashMap<>();
    }

    /**
     * Maakt een nieuw rekeningnummer aan die in de hashmap gezet kan worden
     *
     * @param bankNaam
     * @return Het nummer van de zojuist geopende rekening
     * @throws RemoteException
     */
    @Override
    public synchronized int openRekening(String bankNaam) {
        int newNumber = nextRekeningNummer;
        rekeningNummers.put(newNumber, bankNaam);
        nextRekeningNummer++;
        return newNumber;
    }

    /**
     * Maakt geld als de bank van de tegenrekening geopend is.
     *
     * @param destination
     * @param bedrag
     * @return {@code true} al het geld succesvol is overgemaakt {@code false}
     * als het geld niet overgemaakt kan worden
     * @throws RemoteException
     */
    @Override
    public boolean maakOver(int destination, Money bedrag) throws RemoteException {
        String bank = rekeningNummers.get(destination);
        if (bank != null) {
            try {
                return ((IBalie) Naming.lookup(bank)).muteer(destination, bedrag);
            } catch (NotBoundException | MalformedURLException ex) {
                Logger.getLogger(BankCentrale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
