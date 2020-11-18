/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tblarticle.TblArticleDAO;
import giangvt.tblnotify.TblNotifyDAO;
import giangvt.tblnotify.TblNotifyDTO;
import giangvt.tbluser.TblUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
public class ShowNotiServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ShowNotiServlet.class);
    private final String LOGIN_PAGE = "login.html";
    private final String SHOW_NOTI_PAGE = "notification.jsp";
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
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                String email = (String) session.getAttribute("EMAIL");
                TblNotifyDAO notiDao = new TblNotifyDAO();
                List<TblNotifyDTO> notiList = notiDao.getNotiList(email);
                request.setAttribute("NOTI_LIST", notiList);
                if (notiList != null) {
                    String[] titles = new String[notiList.size()];
                    String[] names = new String[notiList.size()];
                    TblUserDAO userDao = new TblUserDAO();

                    TblArticleDAO postDao = new TblArticleDAO();
                    for (int i = 0; i < notiList.size(); i++) {
                        String title = postDao.getTitle(notiList.get(i).getPostId());
                        String name = userDao.getNameByEmail(notiList.get(i).getEmail());
                        titles[i] = title;
                        names[i] = name;
                    }
                    request.setAttribute("TITLES", titles);
                    request.setAttribute("NAMES", names);
                }
                url = SHOW_NOTI_PAGE;
            }
        } catch (SQLException ex) {
            LOGGER.info("ShowNotiServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("ShowNotiServlet _ Naming: " + ex.getMessage());
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
