/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Class for own exception
 *
 * @author Dominika Matyja
 * @version 1.0
 */
public class MyException extends Exception{
    MyException() {};
    
    MyException(String arg)
    {
        super(arg);
    }
}
