/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblcomment;

import java.io.Serializable;

/**
 *
 * @author MY HP
 */
public class TblCommentDTO implements Serializable {
    private int cmtId;
    private String content;
    private String date;
    private int statusId;
    private String email;
    private int postId;
    private int notifyId;

    public TblCommentDTO() {
    }

    public TblCommentDTO(int cmtId, String content, String date, int statusId, String email, int postId, int notifyId) {
        this.cmtId = cmtId;
        this.content = content;
        this.date = date;
        this.statusId = statusId;
        this.email = email;
        this.postId = postId;
        this.notifyId = notifyId;
    }

    public int getCmtId() {
        return cmtId;
    }

    public void setCmtId(int cmtId) {
        this.cmtId = cmtId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    
    
}
