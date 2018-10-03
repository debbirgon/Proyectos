package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Carer;
import com.example.windows.medispenser.model.Person;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by windows on 02/10/2018.
 * En la base de datos, Cuidador y Dependiente (para esta aplicación: Person) heredan de Persona
 * Para la app se va a usar un único tipo, Person, para Persona y Paciente, de momento, ya que tienen
 * los mismo atributos, y Carer para cuidador.
 * Los servicios de Persona, Cuidador y Dependiente se encontrarán los 3 en esta interfaz PersonService
 */

public interface PersonService {
    //Personas
    @GET("personas")
    Call<Person> getPersona(@Query("id") Integer id_person);

    //Cuidador
    @GET("cuidadores/{id_cuidador}")
    Call<Carer> getCuidador(@Query("id") Integer id_carer);
    @POST("cuidadores")
    Call<Person> postCuidador(@Body Carer carer);
    @DELETE("cuidadores/{id_carer}")
    Call<ResponseBody> deleteCuidador(@Path("id_carer") Integer id_carer);

    //Dependiente/Paciente
    @GET("dependientes/{id_patient}")
    Call<Person> getPatient(@Path("id_patient") Integer id_patient);
    @GET("dependientes/cuidador")
    Call<List<Person>> getAllPatients(@Query("id") Integer id_carer);
    @PUT("dependientes")
    Call<Person> postPatient(@Body Person patient);
    @DELETE("dependientes/{id_patient}")
    Call<ResponseBody> deletePatient(@Path("id_patient") Integer id_patient);



}
