/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enum;
import java.util.*;
import static java.util.Collections.*;

/**
 * Class Model is a class, that converts numbers from system to system
 * It is an attempt to implement Model from MVC architecture
 * 
 * @author Dominika Matyja
 * @version 2.0
 */
public class Model {
    public String number;
    public String method;
    private String output="";
    
    final private String d2b = Enum.d2b.dana();
    
    /**
     * Argumentless constructor used for creating the Tests
     */
    
    public Model()
    {
        method = " ";
        number = "0";
    }
    
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
     * @return true or false
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
        /* Creating list without defining the type,
        in order to use @SuppressWarnings later*/
        List st = new ArrayList();

        /* Initializing temporary value to help with calculations*/
        int tmp;
        
        /* Using Modulus to calculate the remainder and then putting it on
        the stack, then dividing the number by 2 and making iterations until
        the number is not equal to 0*/

        while (numberT != 0)
        {
          tmp = numberT % 2;
          /* Temporary value for String value of Modulus*/
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
        for(String x:stSafe)
        {
            output += x;
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

    private void remove(List<String> stSafe, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
