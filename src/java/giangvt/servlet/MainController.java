/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 *
 * @author MY HP
 */
public class MainController extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(MainController.class);
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String REGISTER_CONTROLLER = "RegisterServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String SEND_CODE_CONTROLLER = "SendCodeServlet";
    private final String ACTIVATE_ACCOUNT_CONTROLLER = "ActivateAccountServlet";
    private final String SEARCH_ARTICLE_CONTROLLER = "SearchArticleServlet";
    private final String CHANGE_NEXT_PAGE_CONTROLLER = "ChangeNextPageServlet";
    private final String CHANGE_PRE_PAGE_CONTROLLER = "ChangePrePageServlet";
    private final String SHOW_DETAIL_CONTROLLER = "ShowDetailServlet";
    private final String POST_ARTICLE_CONTROLLER = "PostArticleServlet";
    private final String LIKE_ARTICLE_CONTROLLER = "LikeArticleServlet";
    private final String DISLIKE_ARTICLE_CONTROLLER = "DislikeArticleServlet";
    private final String REGISTER_PAGE = "register.html";
    private final String VERIFY_PAGE = "verify.jsp";
    private final String POST_PAGE = "post_article.jsp";
    private final String POST_COMMENT_CONTROLLER = "PostCommentServlet";
    private final String DELETE_CMT_CONTROLLER = "DeleteCmtServlet";
    private final String DELETE_ARTICLE_CONTROLLER = "DeleteArticleServlet";
    private final String SHOW_NOTI_CONTROLLER = "ShowNotiServlet";

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
        String button = request.getParameter("btAction");

        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException ex) {
                LOGGER.info("MainController _ FileUploadEx: " + ex.getMessage());
            }

            Iterator iter = items.iterator();
            Hashtable params = new Hashtable();
            String fileName = null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    params.put(item.getFieldName(), item.getString());
                } else {
                    try {
                        String itemName = item.getName();
                        fileName = itemName.substring(
                                itemName.lastIndexOf("\\") + 1);
                        String RealPath = getServletContext().getRealPath("/")
                                + "images\\" + fileName;
                        File savedFile = new File(RealPath);
                        item.write(savedFile);
                    } catch (Exception ex) {
                        LOGGER.info("MainController _ Ex: " + ex.getMessage());
                    }
                }
            }
            button = (String) params.get("btAction");
            request.setAttribute("PARAMS", params);
            request.setAttribute("IMAGE", fileName);
        }

        String url = LOGIN_PAGE;
        try {
            if (button != null) {
                if (button.equals("Sign in")) {
                    url = LOGIN_CONTROLLER;
                } else if (button.equals("Register")) {
                    url = REGISTER_CONTROLLER;
                } else if (button.equals("Logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (button.equals("SendCode")) {
                    url = SEND_CODE_CONTROLLER;
                } else if (button.equals("Confirm")) {
                    url = ACTIVATE_ACCOUNT_CONTROLLER;
                } else if (button.equals("Search")) {
                    url = SEARCH_ARTICLE_CONTROLLER;
                } else if (button.equals("ChangeNextPage")) {
                    url = CHANGE_NEXT_PAGE_CONTROLLER;
                } else if (button.equals("ChangePrePage")) {
                    url = CHANGE_PRE_PAGE_CONTROLLER;
                } else if (button.equals("Detail")) {
                    url = SHOW_DETAIL_CONTROLLER;
                } else if (button.equals("Post")) {
                    url = POST_ARTICLE_CONTROLLER;
                } else if (button.equals("like")) {
                    url = LIKE_ARTICLE_CONTROLLER;
                } else if (button.equals("dislike")) {
                    url = DISLIKE_ARTICLE_CONTROLLER;
                } else if (button.equals("register_page")) {
                    url = REGISTER_PAGE;
                } else if (button.equals("verify_page")) {
                    url = VERIFY_PAGE;
                } else if (button.equals("post_page")) {
                    url = POST_PAGE;
                } else if (button.equals("Post Comment")) {
                    url = POST_COMMENT_CONTROLLER;
                } else if (button.equals("x")) {
                    url = DELETE_CMT_CONTROLLER;
                } else if (button.equals("X")) {
                    url = DELETE_ARTICLE_CONTROLLER;
                } else if (button.equals("Noti")) {
                    url = SHOW_NOTI_CONTROLLER;
                }
            }
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
