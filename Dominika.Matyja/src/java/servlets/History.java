/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import view.RespCreator;

/**
 *
 * @author Dossja
 */
public class History extends HttpServlet {

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
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3030/program","user", "pass"))
        {
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM converTable");
            
            /* Adding data from result set into String, to print it out later*/
            resultData += "<tr> <td>id</td> <td>Input</td> "
                    + "<td>Output</td> <td>Side of Conversion</td> <td>Result</td> </tr>";
            
            /* Adding data till the end of ResultSet*/
            while(rs.next())
            {
                resultData += "<tr> <td>" + rs.getInt("id") + "</td> <td>"
                        + rs.getString("number") + "</td> <td>" 
                        + rs.getString("result") + "</td> <td>" 
                        + rs.getString("sideOfConversion") + "</td> <td>"
                        + rs.getString("history") + "</td> </tr>";
            }
            
            rs.close();
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

}
