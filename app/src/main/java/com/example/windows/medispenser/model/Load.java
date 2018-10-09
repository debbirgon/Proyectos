package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows on 02/10/2018.
 */

public class Load implements Serializable{
    @SerializedName(value = "id", alternate = "ID")
    private Integer id;
    @SerializedName(value = "id_spd", alternate = "ID_SPD")
    private Integer id_spd;
    @SerializedName(value = "id_medicamento", alternate = "ID_MEDICAMENTO")
    private Integer id_med;
    @SerializedName(value = "id_dependiente", alternate = "ID_DEPENDIENTE")
    private Integer id_patient;
    @SerializedName(value = "cantidad", alternate = "CANTIDAD")
    private Integer quantity;
    @SerializedName(value = "nivel", alternate = "NIVEL")
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_spd() {
        return id_spd;
    }

    public void setId_spd(Integer id_spd) {
        this.id_spd = id_spd;
    }

    public Integer getId_med() {
        return id_med;
    }

    public void setId_med(Integer id_med) {
        this.id_med = id_med;
    }

    public Integer getId_patient() {
        return id_patient;
    }

    public void setId_patient(Integer id_patient) {
        this.id_patient = id_patient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
