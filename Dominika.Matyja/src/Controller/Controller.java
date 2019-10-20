/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import View.View;

/**
 * Class used for comunication between Model and View
 * An attempt to create Controller from MVC architecture
 *
 * @author Dominika Matyja
 * @version 1.0
 */
public class Controller {
    final private Model model;
    final private View view;
    
    /**
     * Constructor for the comunication between two classes
     * @param args 
     */
    public Controller(String[] args)
    {
        view = new View(args);
        model = new Model(view.getNumber(), view.getMethod());
        model.getFunc();
        model.convert();
        view.setOutput(model.getResult());
        view.printResult();
    }
}
