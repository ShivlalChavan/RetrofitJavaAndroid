package com.example.testapi.Rest;

public class ApiUtils {

    private static final String BASEURL = "https://api.openweathermap.org/data/2.5/";


    public static  ApiInterface getApiService()
    {
        return  ApiClient.getClient(BASEURL).create(ApiInterface.class);
    }

}
