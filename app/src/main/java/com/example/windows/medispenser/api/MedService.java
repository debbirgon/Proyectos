package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Med;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by windows on 02/10/2018.
 */

public interface MedService {

    @GET("medicamentos/{id_med}")
    Call<Med> getMedByID(@Path("id_med") int id_med);

    @GET("medicamentos/carga")
    Call<Med> getMedByLoad(@Query("id") int id_load);

    @GET("medicamentos/dependiente")
    Call<List<Med>> getListMed(@Query("id") int id_patient);

    @POST("medicamentos")
    Call<Med> postMed(@Body Med med);

    @DELETE("medicamentos/{id_med}")
    Call<ResponseBody> deleteMed(@Path("id_med") int id_med);
}
