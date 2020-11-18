/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tbluser.TblUserDAO;
import giangvt.tbluser.UserRegisterNewError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author MY HP
 */
public class RegisterServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(MainController.class);

    private final String ERROR_PAGE = "register_error.jsp";
    private final String SEND_CODE_CONTROLLER = "SendCodeServlet";

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

        String url = ERROR_PAGE;

        String email = request.getParameter("txtEmail");
        String fullname = request.getParameter("txtFullname");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");

        UserRegisterNewError errors = new UserRegisterNewError();
        boolean foundErr = false;

        try {
            if (email.trim().length() < 6 || email.trim().length() > 50) {
                foundErr = true;
                errors.setEmailLengthErr("Email requires input value length from 6 to 50 chars");
            }
            if (password.length() < 6 || password.length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("Password requires input value length from 6 to 30 chars");
            } else if (!confirm.equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm password must match password");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLengthErr("Fullname requires input value length from 6 to 50 chars");
            }

            if (foundErr) {
                request.setAttribute("REGISTER_ERRORS", errors);
            } else {
                TblUserDAO dao = new TblUserDAO();
                String result = dao.registerAccount(email.toLowerCase(), fullname, password, 1);
                if (result != null) {
                    url = SEND_CODE_CONTROLLER;
                    request.setAttribute("CODE", result);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.info("RegisterServlet _ SQLException: " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " is existed");
                request.setAttribute("REGISTER_ERRORS", errors);
            }
        } catch (NamingException ex) {
            LOGGER.info("RegisterServlet _ NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
