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
 * Servlet for cleaning the values of the text fields
 *
 * @author Dominika Matyja
 * @version 1.0
 */
@WebServlet(name = "Clean", urlPatterns = {"/Clean"})
public class Clean extends HttpServlet {

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
        
        cleanPage(model, creator);
        
        try (PrintWriter out = response.getWriter())
        {
            creator.createResp();
            out.println(creator.getResp());
        }
        
        sess.setAttribute("converter", model);
        sess.setAttribute("creator", creator);
    }

    /**
     * Sets the field in Model and "View" into empty, to clean the page
     * @param model class Model
     * @param creator class RespCraetor ("View" of the application)
     */
    private void cleanPage(Model model, RespCreator creator)
    {
        creator.setError("");
        creator.setViewIN("");
        creator.setViewOUT("");
        
        model.setNumber("");
        model.setOutput("");
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
