package com.example.canine_care.api

import com.example.canine_care.entity.Admin
import com.example.canine_care.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AdminAPI {
    //register Admin
    @POST("admin/register")
    suspend fun registerAdmin(
        @Body admin : Admin
    ): Response<LoginResponse>

    //login Admin
    @FormUrlEncoded
    @POST("admin/login")
    suspend fun checkAdmin(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>
}