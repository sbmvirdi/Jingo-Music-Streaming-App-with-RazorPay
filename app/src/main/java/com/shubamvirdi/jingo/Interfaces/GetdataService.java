package com.shubamvirdi.jingo.Interfaces;

import com.shubamvirdi.jingo.DataModels.JingoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetdataService  {

    @GET("/studio")
    Call<List<JingoModel>> getMusicData();
}
