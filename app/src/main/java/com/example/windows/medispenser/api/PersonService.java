package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Carer;
import com.example.windows.medispenser.model.Patient;
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
 */

public interface PersonService {
    //Personas
    @GET("personas")
    Call<Person> getPerson(@Query("id") Integer id_person);

    //Cuidador
    @GET("cuidadores/{id_cuidador}")
    Call<Carer> getCarer(@Query("id") Integer id_carer);
    @POST("cuidadores")
    Call<Carer> postCarer(@Body Carer carer);
    @DELETE("cuidadores/{id_carer}")
    Call<ResponseBody> deleteCarer(@Path("id_carer") Integer id_carer);

    //Dependiente/Paciente
    @GET("dependientes/{id_patient}")
    Call<Patient> getPatient(@Path("id_patient") Integer id_patient);
    @GET("dependientes/cuidador/{id_carer}")
    Call<List<Patient>> getAllPatients(@Path("id_carer") Integer id_carer);
    @POST("dependientes")
    Call<Patient> postPatient(@Body Patient patient);
    @DELETE("dependientes/{id_patient}")
    Call<ResponseBody> deletePatient(@Path("id_patient") Integer id_patient);



}
