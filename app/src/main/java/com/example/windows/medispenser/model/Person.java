package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 19/09/2018.
 */

public class Person implements Serializable {
    @SerializedName(value = "ID_PERSONA"/*, alternate = "ID"*/)
    private Integer id_person;
    @SerializedName(value = "nombre", alternate = "NOMBRE")
    private String name;
    @SerializedName(value = "apellidos", alternate = "APELLIDOS")
    private String surname;
    @SerializedName(value = "fecha_nacimiento", alternate = "FECHA_NACIMIENTO")
    private String birthday;
    @SerializedName(value = "sexo", alternate = "SEXO")
    private Sex sex;

    public Person(String name, String surname, Integer id_person, String birthday, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.id_person = id_person;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Person(String name, String surname, String birthday, Sex sex) {
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

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
