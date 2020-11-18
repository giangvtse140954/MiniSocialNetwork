/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblnotify;

import java.io.Serializable;

/**
 *
 * @author MY HP
 */
public class TblNotifyDTO implements Serializable {
    private int notifyId;
    private int postId;
    private String email;
    private String date;
    private int type;

    public TblNotifyDTO() {
    }

    public TblNotifyDTO(int notifyId, int postId, String email, String date, int type) {
        this.notifyId = notifyId;
        this.postId = postId;
        this.email = email;
        this.date = date;
        this.type = type;
    }

    public int getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
