/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tbluser;

import giangvt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.naming.NamingException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author MY HP
 */
public class TblUserDAO implements Serializable {

    private int statusId = 0;
    private String email = null;

    public String checkLogin(String email, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String encryptedPass = DigestUtils.sha256Hex(password);

                String sql = "Select email, statusId, name "
                        + "From tblUser "
                        + "Where email = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, encryptedPass);

                rs = stm.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    this.statusId = rs.getInt("statusId");
                    this.email = rs.getString("email");
                    return name;
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
        return null;
    }

    public int getStatusId() {
        return this.statusId;
    }

    public String getEmail() {
        return this.email;
    }

    public String registerAccount(String email, String fullname, String password, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        String result = null;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String encryptedPass = DigestUtils.sha256Hex(password);
                Random random = new Random();
                int num = random.nextInt(999999);
                String code = String.format("%06d", num);

                String sql = "Insert into "
                        + "tblUser(email, name, password, statusId, code) "
                        + "Values(?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, fullname);
                stm.setString(3, encryptedPass);
                stm.setInt(4, status);
                stm.setString(5, code);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = code;
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

    public boolean updateStatus(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Update tblUser "
                        + "Set statusId = 2 "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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

    public boolean checkCode(String email, String code)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select email "
                        + "From tblUser "
                        + "Where code = ? And email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, email);
                rs = stm.executeQuery();
                if (rs.next()) {
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
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
    public String getNameByEmail(String email) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;
        
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select name "
                        + "From tblUser "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getNString("name");
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
        return name;
    }
}
