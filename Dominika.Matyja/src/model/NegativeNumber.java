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
 * @version 2.0
 */
public class NegativeNumber extends Exception{
    NegativeNumber() {};
    
    NegativeNumber(String arg)
    {
        super(arg);
    }
}
