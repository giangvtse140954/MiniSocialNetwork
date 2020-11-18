/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import giangvt.tblarticle.TblArticleDAO;
import giangvt.tblarticle.TblArticleDTO;
import giangvt.tblcomment.TblCommentDAO;
import giangvt.tblcomment.TblCommentDTO;
import giangvt.tblemotion.TblEmotionDAO;
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
public class ShowDetailServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ShowDetailServlet.class);
    private final String LOGIN_PAGE = "login.html";
    private final String SHOW_DETAIL_PAGE = "show_detail.jsp";
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
        String postId = request.getParameter("txtPostId");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblArticleDAO dao = new TblArticleDAO();
                if (postId == null) {
                    url = ERROR_PAGE;
                } else if (!postId.matches("\\d+")) {
                    url = ERROR_PAGE;
                } else {
                    TblArticleDTO dto = dao.getDetail(Integer.parseInt(postId));
                    if (dto == null) {
                        url = ERROR_PAGE;
                    } else {
                        String email = (String) session.getAttribute("EMAIL");
                        TblEmotionDAO emoDao = new TblEmotionDAO();
                        TblCommentDAO cmtDao = new TblCommentDAO();
                        TblUserDAO userDao = new TblUserDAO();

                        int cmt = cmtDao.getANumberOfCmts(Integer.parseInt(postId), 3);
                        int dislike = emoDao.getStatusQuantity(0, Integer.parseInt(postId));
                        int like = emoDao.getStatusQuantity(1, Integer.parseInt(postId));
                        int isLiked = emoDao.checkStatusEmotion(email, Integer.parseInt(postId));
                        List<TblCommentDTO> cmtList = cmtDao.getCmtListByPostId(Integer.parseInt(postId));
                        String postUser = userDao.getNameByEmail(dto.getEmail());
                        String[] names = null;
                        
                        if (cmtList != null) {
                            names = new String[cmtList.size()];

                            // get name
                            for (int i = 0; i < names.length; i++) {
                                names[i] = userDao.getNameByEmail(cmtList.get(i).getEmail());
                            }
                        }
                        
                        request.setAttribute("NAMES", names);
                        request.setAttribute("POSTUSER", postUser);
                        request.setAttribute("CMT", cmt);
                        request.setAttribute("LIKE", like);
                        request.setAttribute("DISLIKE", dislike);
                        request.setAttribute("DETAIL", dto);
                        request.setAttribute("CMTLIST", cmtList);

                        if (isLiked == 1) {
                            request.setAttribute("USERLIKE", "on");
                        } else if (isLiked == 0) {
                            request.setAttribute("USERDISLIKE", "on");
                        }
                        url = SHOW_DETAIL_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.info("ShowDetailServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("ShowDetailServlet _ Naming: " + ex.getMessage());
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
