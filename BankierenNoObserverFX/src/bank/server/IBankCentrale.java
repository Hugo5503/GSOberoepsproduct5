/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.server;

import bank.bankieren.Money;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Ruud
 */
public interface IBankCentrale extends Remote{

    /**
     * Maakt een nieuw rekeningnummer aan die in de hashmap gezet kan worden
     * @param bankNaam
     * @return Het nummer van de zojuist geopende rekening
     * @throws RemoteException
     */
    public int openRekening(String bankNaam) throws RemoteException;
    
    /**
     * Maakt geld als de bank van de tegenrekening geopend is.
     * @param destination
     * @param bedrag
     * @return {@code true} al het geld succesvol is overgemaakt {@code false} als het geld niet overgemaakt kan worden
     * @throws RemoteException
     */
    public boolean maakOver(int destination, Money bedrag) throws RemoteException;
}
