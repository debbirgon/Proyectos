package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by windows on 02/10/2018.
 */

public class Treatment {

    @SerializedName("ID")
    private Integer id;
    @SerializedName("ID_DEPENDIENTE")
    private Integer id_patient;
    @SerializedName("ID_SPD")
    private Integer id_spd;
    @SerializedName("ID_ALIAS")
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
