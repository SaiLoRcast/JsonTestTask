package com.polygalov.jsontesttask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RetrofitInterface {

    @GET("v2/5b3f7df93400001000001b1f")
    Call<List<ApiObject>> getAllPost();
}
