package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 02/10/2018.
 */

public class Carer extends Person implements Serializable{

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public Carer(){}
    public Carer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
