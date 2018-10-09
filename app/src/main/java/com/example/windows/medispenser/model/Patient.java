package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 03/10/2018.
 */

public class Patient extends Person implements Serializable{

    @SerializedName(value = "id", alternate = "ID")
    private Integer id_patient;
    @SerializedName(value = "id_cuidador", alternate = "ID_CUIDADOR")
    private Integer id_carer;
    @SerializedName(value = "alias", alternate = "ALIAS")
    private String alias;


    public Patient(){}

    public Integer getId_patient() {
        return id_patient;
    }

    public void setId_patient(Integer id_patient) {
        this.id_patient = id_patient;
    }

    public Integer getId_carer() {
        return id_carer;
    }

    public void setId_carer(Integer id_carer) {
        this.id_carer = id_carer;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Nombre: " +
                getName() + " " + getSurname()+
                "\nFecha de nacimiento: " + getBirthday() +
                "\nSexo: " + getSex();
    }
}
