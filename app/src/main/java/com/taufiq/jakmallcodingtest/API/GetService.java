package com.taufiq.jakmallcodingtest.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("5")
    Call<JsonRespon> getAllData();
}
