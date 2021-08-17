package com.example.canine_care.api


import com.example.canine_care.entity.Pet
import com.example.canine_care.response.AddPetResponse
import com.example.canine_care.response.DeletePetResponse
import com.example.canine_care.response.GetAllPetResponse
import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PetAPI {
    //Add Pet
    @POST("pet/")
    suspend fun addPet(
        @Header("Authorization") token : String,
        @Body pet : Pet
    ): Response<AddPetResponse>

    //View Pet
    @GET("pet/")
    suspend fun getAllPet(
        @Header("Authorization") token : String,
    ): Response<GetAllPetResponse>

    //Delete Pet
    @DELETE("pet/{id}")
    suspend fun deletePet(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ): Response<DeletePetResponse>

    //Update Pet Details
    @PUT("pet/{id}/update")
    suspend fun updatePet(
        @Header("Authorization") token : String,
        @Path("id") id : String,
        @Body pet : Pet
    ): Response<DeletePetResponse>

    //for uploading image or files
    @Multipart
    @PUT("pet/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}