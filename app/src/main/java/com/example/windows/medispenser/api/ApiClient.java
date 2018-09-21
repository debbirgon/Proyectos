package com.example.windows.medispenser.api;

import com.example.windows.medispenser.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by windows on 21/09/2018.
 */

public class ApiClient {

    public static Retrofit retrofit;

    public static Retrofit getApiClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    }
