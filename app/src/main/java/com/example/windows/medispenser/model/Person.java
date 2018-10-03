package com.example.windows.medispenser.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 19/09/2018.
 */

public class Person implements Serializable {
    @SerializedName("ID")
    private Integer id;
    @SerializedName("NOMBRE")
    private String name;
    @SerializedName("APELLIDOS")
    private String surname;
    @SerializedName("FECHA_NACIMIENTO")
    private String birthday;
    @SerializedName("SEXO")
    private Sexo sex;
    @Nullable
    @SerializedName("id_cuidador")
    private Integer id_cuidador;

    public Person(String name, String surname, Integer id, String birthday, Sexo sex) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Person(String name, String surname, String birthday, Sexo sex) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Person(){
        this.name = "";
        this.surname = "";
        this.birthday = "";
        this.sex = null;
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

    public Sexo getSex() {
        return sex;
    }

    public void setSex(Sexo sex) {
        this.sex = sex;
    }
}
