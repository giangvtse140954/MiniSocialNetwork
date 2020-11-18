/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblnotify;

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
public class TblNotifyDAO implements Serializable {

    public int insertNoti(int postId, String email, String date, int type)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String[] key = {"notifyId"};
        int id = -1;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Into tblNotify(postId, email, date, type) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql, key);
                stm.setInt(1, postId);
                stm.setString(2, email);
                stm.setString(3, date);
                stm.setInt(4, type);

                int isInsert = stm.executeUpdate();
                if (isInsert > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
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
        return id;
    }

    public void deleteNoti(int notifyId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete From tblNotify "
                        + "Where notifyId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, notifyId);

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
    
    public void deleteNotiByPost(int postId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete From tblNotify "
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
    

    public List<TblNotifyDTO> getNotiList(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TblNotifyDTO> list = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select notifyId, postId, email, date, type "
                        + "From tblNotify "
                        + "Where postId In (Select postId From tblArticle Where email = ?) "
                        + "And Not email = ? "
                        + "Order By date Desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, email);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int notiId = rs.getInt("notifyId");
                    int postId = rs.getInt("postId");
                    String emailSender = rs.getString("email");
                    String date = rs.getString("date");
                    int type = rs.getInt("type");
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(new TblNotifyDTO(notiId, postId, emailSender, date, type));
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
    
    public void updateNoti(int type, int notiId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblNotify "
                        + "Set type = ? "
                        + "Where notifyId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, type);
                stm.setInt(2, notiId);

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
