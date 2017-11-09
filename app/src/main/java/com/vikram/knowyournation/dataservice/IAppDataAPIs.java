package com.vikram.knowyournation.dataservice;

/**
 * Created by M1032130 on 10/26/2017.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAppDataAPIs {
    String CONTENT_TYPE = "Content-Type";
    String JSON_TYPE = "application/json";
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<List<RetroResponse>> getCountries();
}
