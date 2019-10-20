/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Stack;

/**
 * Class Model is a class, that converts numbers from system to system
 * It is an attempt to implement Model from MVC architecture
 * 
 * @author Dominika Matyja
 * @version 1.0
 */
public class Model {
    final private String number;
    final private String method;
    private String output="";
    
    final private String d2b = "d2b";
    
    /**
     * Function that saves the users answers into class members, to use them later
     * @param num number to convert
     * @param met method of conversion
     */
    
    public Model(String num, String met)
    {
        method = met;
        number = num;
    }
    
    /**
     * Function that checks the users choosen function and returns true or false
     * If answer is 'd2b' than the Decimal to Binary function will be used
     * For any other answer, the Binary to Decimal function will be used
     * @return 
     */
    
    public boolean getFunc()
    {
        if(method.equals(d2b))
            return false;
        else
            return true;
    }
    
    /**
     * Function for choosing the correct function for our conversion
     */
    
    public void convert()
    {
        if(getFunc())
        {
            Bin2Dec();
        }
        else
        {
            Dec2Bin();
        }
    }
    
    /**
     * Checks if number is negative and throws exception
     * 
     * @param number that is checked
     * @throws MyException when number is lower than 0
     */
    
    public void numberCheck(int number) throws MyException
    {
        if(number < 0)
            throw new MyException("Negative number! "
                    + "You're going to get false result");
    }
    
    /**
     * Function for converting number from Binary to Decimal
     */
    
    public void Bin2Dec()
    {
        /* Initializing temporary number that is an integer*/
        int numberT = 0;
        
        /* Transforming Strign into integer and checking if it is lower than 0,
        if it is, then throws an exception*/
        try
        {
            numberT = Integer.parseInt(number);
            numberCheck(numberT);
        }
        catch(MyException ex)
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
    
    public void Dec2Bin()
    {
        /* Initializing temporary number that is an integer*/
        int numberT = 0;
        
        /* Transforming Strign into integer and checking if it is lower than 0,
        if it is, then throws an exception*/
        try
        {
            numberT = Integer.parseInt(number);
            numberCheck(numberT);
        }
        catch(MyException ex)
        {
            System.err.println(ex);
        }
        /* Creating stack for remebering partial resuts*/
        Stack<Integer> stack = new Stack<Integer>();
        /* Initializing temporary value to help with calculations*/
        int tmp;
        
        /* Using Modulus to calculate the remainder and then putting it on
        the stack, then dividing the number by 2 and making iterations until
        the number is not equal to 0*/

        while (numberT != 0)
        {
          tmp = numberT % 2;
          stack.push(tmp);
          numberT = numberT / 2;
        } 
    
        /* Printing the stack and removing stack elements until it's empty*/
        while (!(stack.isEmpty() ))
        {
          output += stack.pop();
        }
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
