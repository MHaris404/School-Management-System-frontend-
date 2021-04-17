package com.example.assetssm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceEndpoint__Activity_mainOfficerListApp {

    @GET("android/jsonandroid")
    Call<JSON_Activity_mainOfficerListApp> getJSON();
}