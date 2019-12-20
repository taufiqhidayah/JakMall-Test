package com.taufiq.jakmallcodingtest.API;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {

    public  static Retrofit retrofit;
    public  static final String Base_Url ="https://api.icndb.com/jokes/random/";
    public  static  Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit = new  retrofit2.Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
