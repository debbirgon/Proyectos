package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Dose;

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
 * Created by windows on 21/09/2018.
 */

public interface DoseService {

    @GET("dosis/medicamento")
    Call<Dose> getMedDose(@Query("id") int id_med);

    @GET("dosis/tratamiento")
    Call<List<Dose>> getListDoseByTreatment(@Query("id") int id_treatment);

    @PUT("dosis/{id_dose}")
    Call<Dose> putDose(@Path("id_dose") int id_dose);

    @POST("dosis")
    Call<Dose> postDose(@Body Dose dose);

    @DELETE("dosis/{id_dose}")
    Call<ResponseBody> deleteDose(@Path("id_dose") int id_dose);

}
