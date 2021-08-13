package com.example.canine_care.api

import com.example.canine_care.entity.User
import com.example.canine_care.response.GetUserProfileResponse
import com.example.canine_care.response.LoginResponse
import com.example.canine_care.response.UpdateUserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    //register user
    @POST("auth/register")
    suspend fun registerUser(
        @Body user : User
    ): Response<LoginResponse>

    //login user
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun checkUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    //Get user details
    @GET("/me/{id}")
    suspend fun getMe(
        @Path("id") id: String,
        @Header("Authorization") token: String,
    ): Response<GetUserProfileResponse>


    //Update user details
    @PUT("/update/customer/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body user: User
    ): Response<UpdateUserResponse>
}