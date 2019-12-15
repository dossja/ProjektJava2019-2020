/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
import static java.util.Collections.*;

/**
 * Class Model is a class, that converts numbers from system to system
 * It is an attempt to implement Model from MVC architecture
 * 
 * @author Dominika Matyja
 * @version 2.1
 */

public class Model {
    /** number is a number that is written in console by an user*/
    public String number;
    
    /** output is an output of converison*/
    private String output = "";
    
    /** boolean value for side of conversion
        true - Binary to Decimal, false - Decimal to Binary*/
    public boolean sideB2D;
    
    /**
     * Argumentless constructor used for creating the Tests
     */
    
    public Model()
    {
        sideB2D = true;
        number = "";
    }
    
    /**
     * Function that saves the users answers into class members, to use them later
     * @param num number to convert
     * @param met method of conversion
     */
    
    public Model(String num, boolean met)
    {
        number = num;
        sideB2D = met;
    }
    
    /**
     * Method for changing the side of conversion
     * Used for tests
     * @return true or false, depends of the side
     */
    
    public boolean changeSide()
    {
        if (sideB2D)
        {
            sideB2D = false;
        }
        
        else
        {
            sideB2D = true;
        }
        
        return this.sideB2D;
    }
    
    /**
     * Method for returning the side of conversion
     * @return boolean side of converison
     */
    
    public boolean getSide()
    {
        return this.sideB2D;
    }
    
    /**
     * Function for choosing the correct function for our conversion
     * @param met method of conversion
     * @param num number to convert
     * @throws NegativeNumber if number is less than 0
     * @throws NotBinary if for b2d conversion, the input number contains non
     * binary digits
     */
    
    public void convert(boolean met, String num) throws NegativeNumber, NotBinary
    {
        sideB2D = met;
        number = num;
        if(sideB2D)
        {
            bin2Dec();
        }
        else
        {
            dec2Bin();
        }
    }
    
    /**
     * Checks if number is negative and throws exception
     * 
     * @param number that is checked
     * @throws NegativeNumber when number is lower than 0
     */

    public void numberCheck(int number) throws NegativeNumber
    {
        if(number < 0)
            throw new NegativeNumber();
    }

    /**
     * Checks if number is binary and throws exception
     * 
     * @param number as String
     * @throws NotBinary when it contains other digits than 0 or 1
     */
    
    public void binaryCheck(String number) throws NotBinary
    {
        /* Temporary value for number from View*/
        String num = number;
        
        /* Temporary values that splites the text num into standalone digits*/
        String [] strArr = num.split("");
        
        /* List of digits*/
        List <String> list = new ArrayList();
        
        addAll(list, strArr);
        
        for(String i : list)
        {
            if(i.equals(EnumBinary.zero.number())
                    || i.equals(EnumBinary.one.number()))
            {
            }
            else
                throw new NotBinary("This is not a binary number. "
                        + "You're going to get false result");
        }
    }
        
    /**
     * Function for converting number from Binary to Decimal
     */
    
    public void bin2Dec()
    {       
        /* Initializing temporary number that is an integer*/
        int numberT = 0;
        
        /* Transforming Strign into integer and checking if it is lower than 0,
        if it is, then throws an exception*/
        try
        {
            binaryCheck(number);
            numberT = Integer.parseInt(number);
            numberCheck(numberT);
        }
        catch(NotBinary | NegativeNumber ex)
        {
            System.err.println(ex);
        }
        
        /* Initializing result and iterator to compute the output*/
        int result = 0;
        int n = 0;
        /* Initializing temporary value to help with calculations*/
        int tmp;
        
        /* Using Modulus and powers of 2 to calculate the result, iteration
        is repeated until the number is not equal to 0*/
        while(numberT != 0)
        {
            tmp = numberT % 10;
            result += tmp*Math.pow(2, n);
            numberT = numberT / 10;
            n++;
        }
        output = String.valueOf(result);
    }
    
    /**
     * Function for converting number from Decimal to Binary
     */
    
    public void dec2Bin()
    {
        output = "";
        /** Initializing temporary number that is an integer*/
        int numberT = 0;
        
        /** Transforming Strign into integer and checking if it is lower than 0,
        if it is, then throws an exception*/
        try
        {
            numberT = Integer.parseInt(number);
            numberCheck(numberT);
        }
        catch(NegativeNumber ex)
        {
            System.err.println(ex);
        }
        /** Creating list without defining the type,
        in order to use @SuppressWarnings later*/
        List st = new ArrayList();

        /** Initializing temporary value to help with calculations*/
        int tmp;
        
        /* Using Modulus to calculate the remainder and then putting it on
        the stack, then dividing the number by 2 and making iterations until
        the number is not equal to 0*/

        while (numberT != 0)
        {
          tmp = numberT % 2;
          /** Temporary value for String value of Modulus*/
          String tmpNew = String.valueOf(tmp);
          /* Adding value into our "stack"*/
          st.add(tmpNew);
          numberT = numberT / 2;
        } 
        /* Creating new String ArrayList to hold the st values*/
        @SuppressWarnings("unchecked")
        List <String> stSafe  = (List <String>) st;
        /* Reversing the values, to make it easier to use in foreach sentence*/
        reverse(stSafe);
        
        /* Printing the whole stack using foreach*/
        stSafe.forEach((x) -> {
            output += x;
        });
    }
    
    /**
     * Function for returning private value into the Controller
     * @return String with result of conversion
     */
    public String getResult()
    {
        return output;
    }
}