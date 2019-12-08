/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import client.Bin2DecClient;
import java.util.Scanner;

/**
 * Class for taking the users input and displaing the conversion function output
 * An attempt to create View from MVC architecture
 *
 * @author Dominika Matyja
 * @version 3.0
 */
public final class View {
    
    /** Client for connecting with the server */
    Bin2DecClient client;
    
    /** Scanner for interaction with the user */
    Scanner scan;
    
    /**
     * Argumentless constructor for the View
     */
    public View()
    {
        scan = new Scanner(System.in);
    }
    
    /**
     * Prints message for user to see
     * @param msg message to be printed
     */
    
    public void printMsg(String msg)
    {
        System.out.println(msg);
    }
    
    /**
     * Prints exception for user to see
     * @param ex exception to be printed
     */
    
    public void printEx(String ex)
    {
        System.out.println(ex);
    }
    
    /**
     * Method that uses scanner to get request
     * @return user's request
     */
    
    public String getReq()
    {
        return scan.next();
    }            
}
