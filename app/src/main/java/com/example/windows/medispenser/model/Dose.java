package com.example.windows.medispenser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by windows on 21/09/2018.
 */

public class Dose {

    @SerializedName("ID")
    private Integer id;
    @SerializedName("HORA_INICIO")
    private String hora_inicio;
    @SerializedName("VECES_POR_DIA")
    private Integer veces_dia;
    @SerializedName("nombre_medicamento")
    private String nombre_medicamento;
    @SerializedName("ID_MEDICAMENTO")
    private Integer id_medicamento;
    @SerializedName("ID_TRATAMIENTO")
    private Integer id_tratamiento;

    public Dose() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Integer getVeces_dia() {
        return veces_dia;
    }

    public void setVeces_dia(Integer veces_dia) {
        this.veces_dia = veces_dia;
    }

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public Integer getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(Integer id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public Integer getId_tratamiento() {
        return id_tratamiento;
    }

    public void setId_tratamiento(Integer id_tratamiento) {
        this.id_tratamiento = id_tratamiento;
    }
}
