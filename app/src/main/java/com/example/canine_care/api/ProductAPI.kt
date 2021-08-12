package com.example.canine_care.api



import com.example.canine_care.entity.Product
import com.example.canine_care.response.AddProductResponse
import com.example.canine_care.response.DeleteProductResponse
import com.example.canine_care.response.GetAllProductResponse
import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {
    //Add product
    @POST("product/")
    suspend fun addProduct(
        @Header("Authorization") token : String,
        @Body product : Product
    ): Response<AddProductResponse>

    //view product
    @GET("product/")
    suspend fun getAllProduct(
        @Header("Authorization") token : String,
    ): Response<GetAllProductResponse>

    //delete product
    @DELETE("product/{id}")
    suspend fun deleteProduct(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ): Response<DeleteProductResponse>

    //update product detail
    @PUT("product/{id}/update")
    suspend fun updateProduct(
        @Header("Authorization") token : String,
        @Path("id") id : String,
        @Body product : Product
    ): Response<DeleteProductResponse>

    //for uploading image or files
    @Multipart
    @PUT("product/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}