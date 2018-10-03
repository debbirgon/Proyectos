package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Load;

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

public interface LoadService {

    @GET("cargas/dependiente")
    Call<List<Load>> getLoads(@Query("id") int id_patient);

    @PUT("cargas/{id_load}")
    Call<Load> putLoad(@Body Load load);

    @POST("cargas")
    Call<Load> postLoad(@Body Load load);

    @DELETE("cargas/{id_load}")
    Call<ResponseBody> deleteLoad(@Path("id_load") int id_load);
}
