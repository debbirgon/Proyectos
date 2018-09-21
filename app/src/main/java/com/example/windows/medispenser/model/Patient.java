package com.example.windows.medispenser.model;

import java.io.Serializable;

/**
 * Created by windows on 19/09/2018.
 */

public class Patient implements Serializable {
    private String name;
    private String dni;
    private String birthday;

    public Patient(String name, String dni, String birthday) {
        this.name = name;
        this.dni = dni;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
