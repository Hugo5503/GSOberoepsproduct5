/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import javafx.application.Application;

/**
 *
 * @author Ruud
 */
public class BankCentraleServer {

   private static boolean interrupted = false;
    
    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.MalformedURLException
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException, Exception {
        LocateRegistry.createRegistry(1099);
        IBankCentrale centrale = new BankCentrale();
        Naming.rebind("Centrale", centrale);
        System.out.println("Centrale server started!");
        
        Application.launch(BalieServer.class);
    }
    
}
