/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Enum for checking in the controller and the model,
 * if the method for conversion is correct.
 * 
 * @author Dominika Matyja
 * @version 2.0
 */
public enum Enum {
    d2b;
    
    public String dana()
    {
        switch(this)
        {
            case d2b: return "d2b";
            default: return "none";
        }
    }
}
