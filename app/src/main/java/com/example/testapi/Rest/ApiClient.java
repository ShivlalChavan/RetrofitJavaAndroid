package com.example.testapi.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    final static OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                 .connectTimeout(20,TimeUnit.SECONDS)
                 .build();

    static Gson gson = new GsonBuilder()
                        .setLenient()
                         .create();

    public static  Retrofit getClient(String baseUrl){
        if(retrofit == null){
             retrofit = new  Retrofit.Builder()
                         .baseUrl(baseUrl).client(okhttpClient)
                         .addConverterFactory(GsonConverterFactory.create(gson))
                          .build();
        }
         return  retrofit;
    }

}
