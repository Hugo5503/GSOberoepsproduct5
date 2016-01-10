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
    public int openRekening(String bankNaam) throws RemoteException;
    
    public boolean maakOver(int destination, Money bedrag) throws RemoteException;
}
