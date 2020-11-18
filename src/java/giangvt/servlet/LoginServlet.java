/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tbluser.TblUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author MY HP
 */
public class LoginServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(MainController.class);

    private final String SEARCH_PAGE = "SearchArticleServlet";
    private final String ERROR = "login_error.jsp";

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
        PrintWriter out = response.getWriter();

        String url = ERROR;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        try {
            TblUserDAO dao = new TblUserDAO();
            String fullname = dao.checkLogin(email.toLowerCase(), password);
            if (fullname != null) {
                url = SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("FULL_NAME", fullname);
                session.setAttribute("EMAIL", dao.getEmail());

                int status = dao.getStatusId();
                if (status == 1) {
                    session.setAttribute("STATUS", dao.getStatusId());
                } else {
                    session.setAttribute("STATUS", null);
                }
            }
        } catch (SQLException ex) {
            LOGGER.info("LoginServlet _ SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("LoginServlet _ NamingException: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
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
