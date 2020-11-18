/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tblcomment.TblCommentDAO;
import giangvt.tblnotify.TblNotifyDAO;
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
public class DeleteCmtServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(DeleteCmtServlet.class);
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "error_page.jsp";

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
        String url = LOGIN_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String cmtId = request.getParameter("txtCmtId");
                if (cmtId == null) {
                    url = ERROR_PAGE;
                } else if (!cmtId.matches("\\d+")) {
                    url = ERROR_PAGE;
                } else {
                    TblCommentDAO dao = new TblCommentDAO();
                    boolean result = dao.updateCmt(Integer.parseInt(cmtId));
                    String notiId = request.getParameter("txtNotifyId");

                    if (result) {
                        TblNotifyDAO notiDao = new TblNotifyDAO();
                        notiDao.deleteNoti(Integer.parseInt(notiId));
                        String postId = request.getParameter("txtPostId");
                        String urlRewriting = "ShowDetailServlet?txtPostId=" + postId;
                        url = urlRewriting;
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.info("DeleteCmtServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("DeleteCmtServlet _ Naming: " + ex.getMessage());
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
