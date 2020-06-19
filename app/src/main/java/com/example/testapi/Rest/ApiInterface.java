package com.example.testapi.Rest;

import com.example.testapi.Model.WeatherData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("weather?q=Pune&appid=7fa83349f35e368771d161b69e02c48e&units=metric")
    Call<WeatherData> getWeatherData();

}
