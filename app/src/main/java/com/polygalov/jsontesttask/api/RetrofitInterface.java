package com.polygalov.jsontesttask.api;

import com.polygalov.jsontesttask.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("v2/5b3f7df93400001000001b1f")
    Call<List<ApiObject>> getAllPost();

    @GET("v2/5bae0f5c33000063000eb812")
    Call<Example> getFullJson();

}
