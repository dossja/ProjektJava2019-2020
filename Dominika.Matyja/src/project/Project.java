/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import controller.Controller;

/**
 * Main class for starting the program
 *  
 * @author Dominika Matyja
 * @version 1.0
 */
public class Project {

    /**
     * Class for creating the controller
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller(args);
    }
    
}
