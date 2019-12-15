/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class for own exception
 * It is called if for binary to decimal conversion
 * user input a non binary number
 * 
 * @author Dominika Matyja
 * @version 2.0
 */
public class NotBinary extends Exception{
    NotBinary() {};
    
    /**
     * Constructor with one argument
     * @param arg message with error
     */
    NotBinary(String arg)
    {
        super(arg);
    }
}
