package com.example.a201928015_mvvm_hw.Interfaces;

import com.example.a201928015_mvvm_hw.Database.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatAPI {
    @GET("search")
    Call<List<Model>> getImgs(@Query("limit") int limit);
}
