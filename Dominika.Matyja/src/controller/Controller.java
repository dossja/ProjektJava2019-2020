/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Model;
import view.View;

/**
 * Class used for communication between Model and View
 * An attempt to create Controller from MVC architecture
 *
 * @author Dominika Matyja
 * @version 2.1
 */
public class Controller {
    final private Model model;
    final private View view;
    
    /**
     * Constructor for the communication between two classes
     * @param args args from the console
     */
    public Controller(String[] args)
    {
        view = new View(args);
        model = new Model();
        model.convert(view.getMethod(), view.getNumber());
        view.setOutput(model.getResult());
        view.printResult();
    }
}
