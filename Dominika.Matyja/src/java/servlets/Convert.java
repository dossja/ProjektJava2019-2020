package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
import model.NegativeNumber;
import model.NotBinary;
import view.RespCreator;

/**
 * Servlet, that realizes conversion:
 * from binary to decimal
 * and from decimal to binary
 *
 * @author Dominika Matyja
 * @version 2.0
 */
@WebServlet(name = "Convert", urlPatterns = {"/Convert"})
public class Convert extends HttpServlet {
    
    /* id is an id of an element in Table*/
    int id = 0;
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
        History history;
        HttpSession sess = request.getSession(true);
        
        Object sessConv = sess.getAttribute("converter");
        Object sessCreat = sess.getAttribute("creator");
        
        if(sessConv == null)
        {
            model = new Model();
            clearHistoryTable();
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
        
        useCookies(creator, request, response);
        String viewIN = request.getParameter("viewIN");
        this.doConversion(model, creator, viewIN);
        
        try (PrintWriter out = response.getWriter())
        {
            creator.createResp();
            out.println(creator.getResp());
        }
        
        sess.setAttribute("converter", model);
        sess.setAttribute("creator", creator);
        
        creator.setError("");
    }
    
    /**
     * Converts number from user.
     * Uses proper methods from model and view of the application
     * 
     * @param model is an object of a "Model" class responsible for conversion
     * @param creator is an object of "View" class responsible for HTML
     * @param viewIN String with number to convert giver by user
     */
    
    private void doConversion(Model model, RespCreator creator, String viewIN)
    {
        boolean b2d = model.getSide();
        creator.setError("");
        creator.setViewIN(viewIN);
        
        try
        {
            if(b2d)
            {
                model.setNumber(viewIN);
                model.binaryCheck(viewIN);
                int num = Integer.parseInt(viewIN);
                model.numberCheck(num);
                model.bin2Dec();
                creator.setViewOUT(model.getResult());
            }
            else
            {
                model.setNumber(viewIN);
                int num = Integer.parseInt(viewIN);
                model.numberCheck(num);
                model.dec2Bin();
                creator.setViewOUT(model.getResult());
            }

            insertHistoryValues(model);
        }
        catch (NotBinary | NegativeNumber | NumberFormatException
                | NullPointerException | IndexOutOfBoundsException ex)
        {
            creator.setError("Wrong number!\n" + ex);
        }
    }
    
    /**
     * Uses saved cookies to know, how many conversions have been done
     * Displays the number for the View
     * 
     * @param creator class for "View"
     * @param request from servlet
     * @param response from servlet
     */
    
    private void insertHistoryValues(Model model)
    {
        /* Loading the JDBC driver*/
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex)
        {
           System.err.println(ex.getMessage());
           return;
        }
        
        /* Making a connection to a DataBase*/
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bin2decwebdb","root", "pass"))
        {
            Statement statement = con.createStatement();
            
            id++;
            String number = model.getNumber();
            String result = model.getResult();
            
            String sideOfConversion;
            if (model.getSide() == true)
                sideOfConversion = "b2d";
            else
                sideOfConversion = "d2b";
            
            statement.execute("INSERT INTO convertTable(id, number, result, sideOfConversion) VALUES ('" + id + "', '" 
                    + number + "', '" + result + "', '" + sideOfConversion
                    + "')");
            
            System.out.println("Values inserted into DataBase!");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    private void clearHistoryTable()
    {
        /* Loading the JDBC driver*/
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        /* Making a connection to a DataBase*/
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bin2decwebdb","root", "pass"))
        {
            Statement statement = con.createStatement();
            
            statement.execute("DELETE * FROM convertTable");
            
            System.out.println("Values deleted from DataBase!");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    private void useCookies(RespCreator creator, HttpServletRequest request,
            HttpServletResponse response)
    {
        int numberOfConversions = 0;
        Cookie[] cookies = request.getCookies();
        
        for (Cookie cook : cookies)
        {
            if (cook.getName().equals("conversions"))
            {
                numberOfConversions = Integer.parseInt(cook.getValue());
                break;
            }
        }
        creator.setNumberOfConversions(numberOfConversions);
        numberOfConversions++;
        Cookie cookie = new Cookie("conversions", String.valueOf(numberOfConversions));
        response.addCookie(cookie);
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
