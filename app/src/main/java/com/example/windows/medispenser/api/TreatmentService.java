package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Treatment;

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

public interface TreatmentService {

    @GET("tratamientos/dependiente")
    Call<List<Treatment>> getTreatments(@Query("id") int id_patient);

    @POST("tratamientos")
    Call<Treatment> postTreatment(@Body Treatment treatment);

    @DELETE("tratamientos/{id_treatment}")
    Call<ResponseBody> deleteTreatment(@Path("id_treatment") int id_treatment);

}
