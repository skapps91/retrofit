package com.skapps.retrofittutorial.resources.retrofit;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface RetrofitWebService {

    @FormUrlEncoded
    @POST("/insert.php")
    public void insert(
            @Field("name") String name,
            @Field("age") String age,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/select.php")
    public void selectData(
            @Field("data") String data,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/update.php")
    public void update(
            @Field("id") String id,
            @Field("name") String name,
            @Field("age") String age,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/delete.php")
    public void delete(
            @Field("id") String id,
            Callback<Response> callback);

}
