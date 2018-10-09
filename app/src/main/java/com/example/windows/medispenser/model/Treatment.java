package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 02/10/2018.
 */

public class Treatment implements Serializable{

    @SerializedName(value = "id", alternate = "ID")
    private Integer id;
    @SerializedName(value = "id_dependiente", alternate = "ID_DEPENDIENTE")
    private Integer id_patient;
    @SerializedName(value = "id_spd", alternate = "ID_SPD")
    private Integer id_spd;
    @SerializedName(value = "alias", alternate = "ALIAS")
    private String name;

    public Treatment(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_patient() {
        return id_patient;
    }

    public void setId_patient(Integer id_patient) {
        this.id_patient = id_patient;
    }

    public Integer getId_spd() {
        return id_spd;
    }

    public void setId_spd(Integer id_spd) {
        this.id_spd = id_spd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
