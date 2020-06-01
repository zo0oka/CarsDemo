package com.zo0okadev.carsdemo.remote;

import com.zo0okadev.carsdemo.model.CarsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public interface ApiService {

    @GET("cars")
    Call<CarsResponse> getCars(@Query("page") int page);
}
