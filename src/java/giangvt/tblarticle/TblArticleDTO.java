/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangvt.tblarticle;

import java.io.Serializable;

/**
 *
 * @author MY HP
 */
public class TblArticleDTO implements Serializable {
    private int postId;
    private String image;
    private String title;
    private String description;
    private String email;
    private int statusId;
    private String date;

    public TblArticleDTO() {
    }

    public TblArticleDTO(int postId, String image, String title, String description, String email, int statusId, String date) {
        this.postId = postId;
        this.image = image;
        this.title = title;
        this.description = description;
        this.email = email;
        this.statusId = statusId;
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
