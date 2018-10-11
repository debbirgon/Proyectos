package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Load;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by windows on 02/10/2018.
 */

public interface LoadService {

    @GET("cargas/dependiente")
    Call<List<Load>> getLoads(@Query("id") int id_patient);

    @GET("cargas/dependiente")
    Call<List<Load>> getLoadsByLevel(@QueryMap Map<String, Integer> queryMap);

    @PUT("cargas/{id_load}")
    Call<Load> putLoad(@Body Load load);

    @POST("cargas")
    Call<Load> postLoad(@Body Load load);

    @DELETE("cargas/{id_load}")
    Call<ResponseBody> deleteLoad(@Path("id_load") int id_load);
}
