/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import view.RespCreator;

/**
 * Servlet for changing the side of conversion (from b2d to d2b and vice versa)
 *
 * @author Dominika Matyja
 * @version 2.0
 */
@WebServlet(name = "Change", urlPatterns = {"/Change"})
public class Change extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Model model;
        RespCreator creator;
        HttpSession sess = request.getSession(true);
                
        Object sessConv = sess.getAttribute("converter");
        Object sessCreat = sess.getAttribute("creator");
                
        if(sessConv == null)
        {
            model = new Model();
        }
        else
        {
            model = (Model) sessConv;
        }
        
        if(sessCreat == null)
        {
            creator = new RespCreator();
        }
        else
        {
            creator = (RespCreator) sessCreat;
        }
        
        changeDirection(model, creator);
        
        try (PrintWriter out = response.getWriter())
        {
            creator.createResp();
            out.println(creator.getResp());
        }
        
        sess.setAttribute("converter", model);
        sess.setAttribute("creator", creator);
    }
    
    /**
     * Swaps text field labels and erases values of text fields
     * Uses methods from "View" and "Model" to do so.
     * 
     * @param model an object of Model class, responsible for conversion
     * @param creator an object of "View" class, used for HTML page
     */
    
    private void changeDirection(Model model, RespCreator creator)
    {
        String tmp = creator.getNumberIN();
        creator.setNumberIN(creator.getNumberOUT());
        creator.setNumberOUT(tmp);
        
        creator.setViewIN("");
        creator.setViewOUT("");
        
        creator.setError("");
        
        model.setNumber("");
        model.setOutput("");
        
        model.changeSide();
    }
      

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
