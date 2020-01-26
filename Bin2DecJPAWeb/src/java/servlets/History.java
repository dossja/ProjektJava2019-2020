/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Converttable;
import model.Model;
import view.RespCreator;

/**
 *
 * @author Dominika Matyja
 * @version 2.0
 */
public class History extends HttpServlet {

    @PersistenceContext(unitName = "Bin2DecJPAWebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
        
        creator.addNewHistoryElement(this.createHistory(model, creator));
        
        try (PrintWriter out = response.getWriter())
        {
            creator.createResp();
            out.println(creator.getResp());
        }
        
        sess.setAttribute("converter", model);
        sess.setAttribute("creator", creator);
    }

    private String createHistory(Model model, RespCreator view)
    {
        /* Loading the JDBC driver*/
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex)
        {
            return ex.getMessage();
        }
        
        String resultData = "";
        
        /* Making a connection to a DataBase*/
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bin2decwebdb","root", "pass"))
        {
          
            /* Adding data into String, to print it out later*/
            resultData += "<tr> <td>id</td> <td>Input</td> "
                    + "<td>Output</td> <td>SideofConversion</td> </tr>";
            
            /* Sending SQL Query to get the results from database*/
            TypedQuery<Converttable> query = em.createQuery("SELECT c FROM Converttable c", Converttable.class);
            List<Converttable> results = query.getResultList();
            
            /* Adding data till the end of query*/
            for(Converttable c : results)
            {
                resultData += "<tr> <td>" + c.getId() + "</td> <td>"
                        + c.getNumber() + "</td> <td>" 
                        + c.getResult() + "</td> <td>" 
                        + c.getSideOfConversion() + "</td> </tr>";
            }
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        return resultData;
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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
