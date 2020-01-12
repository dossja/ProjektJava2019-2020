/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * EnumBinary for checking if the number is binary (contains only "0" and "1")
 * 
 * @author Dominika Matyja
 * @version 2.1
 */

public enum EnumBinary {
    /** zero means number 0*/
    zero,
    /** one means number 1*/
    one;
    
    /**
     * Method for returning the String value for different enums
     * @return String value
     */
    
    public String number()
    {
        switch(this)
        {
            case zero:
                return "0";
            case one:
                return "1";
            default: 
                return "false";
        }
    }
}
