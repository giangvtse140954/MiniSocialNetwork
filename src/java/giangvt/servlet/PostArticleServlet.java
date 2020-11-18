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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Hashtable;
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
public class PostArticleServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(PostArticleServlet.class);
    private final String LOGIN_PAGE = "login.html";
    private final String POST_ARTICLE_PAGE = "post_article.jsp";

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

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        String url = LOGIN_PAGE;
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                Hashtable params = (Hashtable) request.getAttribute("PARAMS");
                
                String image = (String) request.getAttribute("IMAGE");
                String title = (String) params.get("txtTitle");
                String description = (String) params.get("txtDescription");
                String email = (String) session.getAttribute("EMAIL");
                
                TblArticleDAO dao = new TblArticleDAO();
                TblArticleDTO flag = dao.postArticle(image, title, description, email, 3, date + "");
                if (flag != null) {
                    String urlRewriting = "ShowDetailServlet?txtPostId=" + flag.getPostId();
                    url = urlRewriting;
                    request.setAttribute("DETAIL", flag);
                } else {
                    url = POST_ARTICLE_PAGE;
                }
            }
        } catch (SQLException ex) {
            LOGGER.info("PostArticleServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("PostArticleServlet _ Naming: " + ex.getMessage());
        } catch (Exception ex) {
            LOGGER.info("PostArticleServlet _ Ex: " + ex.getMessage());
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
