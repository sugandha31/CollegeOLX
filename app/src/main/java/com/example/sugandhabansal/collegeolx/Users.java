package com.example.sugandhabansal.collegeolx;

import java.util.List;

/**
 * Created by Sugandha Bansal on 7/30/2017.
 */

public class Users {

    String description;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String contact;
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    String imgUrl;
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Users(String description, String contact,String imgUrl){
        this.description = description;
        this.contact = contact;
        this.imgUrl = imgUrl;
    }

}
