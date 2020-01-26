/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class for own exception
 * It is called when negative number is used
 *
 * @author Dominika Matyja
 * @version 3.0
 */
public class NegativeNumber extends Exception
{
    /**
     * Argumentless constructor, which also have an empty body
     */
    NegativeNumber() {};
    
    /**
     * Constructor with one argument, which invokes constuctor from base class
     * @param arg message from exception
     */
    
    NegativeNumber(String arg)
    {
        super(arg);
    }
}
