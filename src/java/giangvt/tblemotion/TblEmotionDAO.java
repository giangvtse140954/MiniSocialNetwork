/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblemotion;

import giangvt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author MY HP
 */
public class TblEmotionDAO implements Serializable {
    public int getNoti(int postId, String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int notiId = -1;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select notifyId "
                        + "From tblEmotion "
                        + "Where postId = ? And email = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
                stm.setString(2, email);

                rs = stm.executeQuery();
                if (rs.next()) {
                    notiId = rs.getInt("notifyId");
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
        return notiId;
    }

    // get a number of likes or dislikes
    public int getStatusQuantity(int isLiked, int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int row = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select COUNT(emoId) As total "
                        + "From tblEmotion "
                        + "Where isLiked = ? And postId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, isLiked);
                stm.setInt(2, postId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    row = rs.getInt("total");
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
        return row;
    }

    // check whether email likes or dislikes post or not
    public int checkStatusEmotion(String email, int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int row = -1;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select isLiked "
                        + "From tblEmotion "
                        + "Where email = ? And postId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, postId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    row = rs.getInt("isLiked");
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
        return row;
    }

    public boolean updateEmotion(String email, int postId, boolean isLiked)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblEmotion "
                        + "Set isLiked = ? "
                        + "Where email = ? And postId = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, isLiked);
                stm.setString(2, email);
                stm.setInt(3, postId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public boolean deleteEmotion(String email, int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete From tblEmotion "
                        + "Where email = ? And postId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, postId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public boolean insertEmotion(String email, int postId, boolean isLiked, String date, int notifyId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Into tblEmotion(isLiked, date, postId, email, notifyId) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, isLiked);
                stm.setString(2, date);
                stm.setInt(3, postId);
                stm.setString(4, email);
                stm.setInt(5, notifyId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

    public void deleteEmoByPostId(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete From tblEmotion "
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
