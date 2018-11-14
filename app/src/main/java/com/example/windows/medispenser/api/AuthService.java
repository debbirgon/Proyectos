package com.example.windows.medispenser.api;

import com.example.windows.medispenser.model.Carer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by windows on 13/11/2018.
 */

public interface AuthService {

    @POST ("auth")
    Call<Integer>  doLogin(@Body Carer carer);
}
