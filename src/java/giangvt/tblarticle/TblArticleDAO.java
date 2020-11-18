/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblarticle;

import giangvt.util.DBHelper;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author MY HP
 */
public class TblArticleDAO implements Serializable {

    private List<TblArticleDTO> list = null;

    public List<TblArticleDTO> getList() {
        return this.list;
    }

    public int searchArticle(String searchValue, int pageNum, int rowsOfPage)
            throws SQLException, NamingException {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        int count = 0;

        try {

            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "{call GetArticle(?, ?, ?)}";
                stm = con.prepareCall(sql);
                stm.setInt(1, pageNum);
                stm.setInt(2, rowsOfPage);
                stm.setString(3, searchValue);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int postId = rs.getInt("postId");
                    String image = rs.getString("image");
                    String title = rs.getNString("title");
                    String description = rs.getNString("description");
                    String email = rs.getString("email");
                    int statusId = rs.getInt("statusId");
                    String date = rs.getDate("date") + "";

                    TblArticleDTO article = new TblArticleDTO(postId, image, title, description, email, statusId, date);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(article);
                    count++;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return count;
    }

    public TblArticleDTO getDetail(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblArticleDTO dto = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select postId, image, title, description, email, statusId, date "
                        + "From TblArticle "
                        + "Where postId = ? And statusId = 3";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String image = rs.getString("image");
                    String title = rs.getNString("title");
                    String description = rs.getNString("description");
                    String email = rs.getString("email");
                    int statusId = rs.getInt("statusId");
                    String date = rs.getDate("date") + "";
                    dto = new TblArticleDTO(postId, image, title, description, email, statusId, date);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return dto;
    }

    // insert a article in table
    public TblArticleDTO postArticle(String image, String title, String description,
            String email, int statusId, String date)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblArticleDTO flag = null;
        String[] key = {"postId"};

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblArticle(image, title, description, email"
                        + ", statusId, date) "
                        + "Values(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql, key);
                stm.setString(1, image);
                stm.setNString(2, title);
                stm.setNString(3, description);
                stm.setNString(4, email);
                stm.setInt(5, statusId);
                stm.setString(6, date);

                int isInserted = stm.executeUpdate();
                if (isInserted > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        int postId = rs.getInt(1);
                        flag = new TblArticleDTO(postId, image, title, description, email, statusId, date);
                    }
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return flag;
    }
    
    public boolean updateArticleStatus(int postId, String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblArticle "
                        + "Set statusId = 4"
                        + "Where postId = ? And email = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }
    
    public String getTitle(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String title = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select title "
                        + "From tblArticle "
                        + "Where postId = ? "
                        + "Order By date Desc";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    title = rs.getString("title");
                }
                
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return title;
    }
}
