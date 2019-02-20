package com.rentavehicle.web.dto;

import com.rentavehicle.model.User;

public class UserMessageDTO {

    private String username;
    private String doc;

    public UserMessageDTO (User user) {
        this.username = user.getUsername();
        this.doc = user.getDocImage();
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
