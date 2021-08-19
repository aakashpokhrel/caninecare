package com.example.canine_care.api


import com.example.canine_care.entity.Appointment
import com.example.canine_care.response.AddAppointmentResponse
import com.example.canine_care.response.DeleteAppointmentResponse
import com.example.canine_care.response.GetAllAppointmentResponse
import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface AppointmentAPI {
    //Add appointment
    @POST("appointment/")
    suspend fun addAppointment(
        @Header("Authorization") token : String,
        @Body appointment : Appointment
    ): Response<AddAppointmentResponse>

    //view appointment
    @GET("appointment/")
    suspend fun getAllAppointment(
        @Header("Authorization") token : String,
    ): Response<GetAllAppointmentResponse>

    //delete appointment
    @DELETE("appointment/{id}")
    suspend fun deleteAppointment(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ): Response<DeleteAppointmentResponse>

    //update appointment detail
    @PUT("appointment/{id}/update")
    suspend fun updateAppointment(
        @Header("Authorization") token : String,
        @Path("id") id : String,
        @Body appointment : Appointment
    ): Response<DeleteAppointmentResponse>

    //for uploading image or files
    @Multipart
    @PUT("appointment/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}