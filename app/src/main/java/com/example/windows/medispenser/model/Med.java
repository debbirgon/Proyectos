package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by windows on 02/10/2018.
 */

public class Med {

    @SerializedName("ID")
    private Integer id;
    @SerializedName("NOMBRE")
    private String name;

    public  Med(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
