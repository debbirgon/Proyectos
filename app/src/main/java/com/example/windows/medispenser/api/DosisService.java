package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Dosis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by windows on 21/09/2018.
 */

public interface DosisService {

    @GET("dosis/dependiente/{id_dependiente}")
    Call<List<Dosis>> listDosis(@Path("id_dependiente") int id_dependiente);

}
