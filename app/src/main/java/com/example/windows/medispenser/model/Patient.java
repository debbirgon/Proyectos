package com.example.windows.medispenser.model;

import java.io.Serializable;

/**
 * Created by windows on 19/09/2018.
 */

public class Patient implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private String birthday;

    public Patient(String name, String surname, Integer id, String birthday) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthday = birthday;
    }

    public Patient(){
        this.name = "";
        this.id = null;
        this.birthday = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
