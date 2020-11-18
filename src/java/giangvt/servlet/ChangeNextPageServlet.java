/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tblarticle.TblArticleDAO;
import giangvt.tblarticle.TblArticleDTO;
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
public class ChangeNextPageServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ChangeNextPageServlet.class);
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE = "error_page.jsp";
    private final int ROWSOFPAGE = 20;

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
                String page = request.getParameter("page");
                String searchValue = request.getParameter("txtSearchValue");
                TblArticleDAO dao = new TblArticleDAO();

                int count = 0;
                int num = 1;
                if (page == null) {
                    url = ERROR_PAGE;
                } else if (page.equals("")) {
                    count = dao.searchArticle(searchValue, 2, ROWSOFPAGE);
                    if (count >= 1) {
                        num = 2;
                    } else {
                        dao.searchArticle(searchValue, 1, ROWSOFPAGE);
                    }
                } else if (!page.matches("\\d+")) {
                    url = ERROR_PAGE;
                } else {
                    int pageNum = Integer.parseInt(page);
                    count = dao.searchArticle(searchValue, pageNum + 1, ROWSOFPAGE);
                    if (count >= 1) {
                        num = pageNum + 1;
                    } else {
                        dao.searchArticle(searchValue, pageNum, ROWSOFPAGE);
                        num = pageNum;
                    }
                }

                url = SEARCH_PAGE;
                List< TblArticleDTO> list = dao.getList();
                request.setAttribute("LIST", list);
                request.setAttribute("PAGE", num);
            }
        } catch (SQLException ex) {
            LOGGER.info("ChangeNextPageServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("ChangeNextPageServlet _ Naming: " + ex.getMessage());
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
