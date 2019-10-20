/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Scanner;

/**
 * Class for taking the users input and displaing the convesion function output
 * An attempt to create View form MVC architecture
 *
 * @author Dominika Matyja
 * @version 1.0
 */
public class View {
    
    /* Strings to hold users intput*/
    private String method;
    private String number;
    
    /* String to hold Models output*/
    private String output;
    
    /**
     * Constructor for the View, with an exception for wrong length of args
     * @param args arguments from command line
     */
    public View(String[] args)
    {
        try
        {
            method = args[0];
            number = args[1];
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            getInput(args.length);
        }
        
    }
    
    /**
     * Function for getting the output from the user
     * @param lngth length
     */
    
    public void getInput(int lngth)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write your choosen method:"
                + "\n\tb2d - binary to decimal"
                + "\n\td2b - decimal to binary");
        method = scanner.next();
            
        System.out.println("Write your number (must be a positive integer):");
        number = scanner.next();
        
        scanner.close();
    }
    
    /**
     * Function for returning the number from users input
     * @return number
     */
    
    public String getNumber()
    {
        return number;
    }
    
    /**
     * Function for returning the choosen method from users input
     * @return method
     */
    
    public String getMethod()
    {
        return method;
    }
    
    /**
     * Function that gets the output from the Model using Controller
     * @param output for the convertion function
     */
    
    public void setOutput(String output)
    {
        this.output = output;
    }
    
    /**
     * Function for printing the result of convertion
     */
    
    public void printResult()
    {
        System.out.println("Your number " + number);
        System.out.println("Is converted into: ");
        System.out.println(output);
    }
}
