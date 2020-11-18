/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblcomment;

import giangvt.util.DBHelper;
import java.io.Serializable;
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
public class TblCommentDAO implements Serializable {

    // insert a comment on a post in database
    public boolean postComment(String content, String date, int status, String email, int postId, int notifyId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Into tblComment(content, date, statusId, email, postId, notifyId) "
                        + "Values(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setNString(1, content);
                stm.setString(2, date);
                stm.setInt(3, status);
                stm.setString(4, email);
                stm.setInt(5, postId);
                stm.setInt(6, notifyId);

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

    public int getANumberOfCmts(int postId, int statusId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select COUNT(cmtId) As total "
                        + "From tblComment "
                        + "Where postId = ? And statusId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.setInt(2, statusId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("total");
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
        return total;
    }

    public List<TblCommentDTO> getCmtListByPostId(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<TblCommentDTO> list = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select cmtId, content, date, statusId, email, postId, notifyId "
                        + "From tblComment "
                        + "Where postId = ? And statusId = 3 "
                        + "Order By date";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int cmtId = rs.getInt("cmtId");
                    String content = rs.getNString("content");
                    String date = rs.getString("date");
                    int statusId = rs.getInt("statusId");
                    String email = rs.getString("email");
                    int notiId = rs.getInt("notifyId");
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(new TblCommentDTO(cmtId, content, date, statusId, email, postId, notiId));
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
        return list;
    }

    public boolean updateCmt(int cmtId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblComment "
                        + "Set statusId = 4"
                        + "Where cmtId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, cmtId);
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
    
    public void updateCmtByPostId(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblComment "
                        + "Set statusId = 4, notifyId = null "
                        + "Where postId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.executeUpdate();

            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
    }

}
