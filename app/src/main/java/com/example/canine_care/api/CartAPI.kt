package com.example.canine_care.api

import com.example.canine_care.entity.Cart
import com.example.canine_care.response.AddCartResponse
import com.example.canine_care.response.DeleteCartResponse
import com.example.canine_care.response.GetCartItemsResponse
import retrofit2.Response
import retrofit2.http.*

interface CartAPI {
    @POST("/cart/add")
    suspend fun addItemToCart(
        @Header("Authorization") token: String,
        @Body cart: Cart
    ): Response<AddCartResponse>

    @GET("/cart/")
    suspend fun getCartItems(
        @Header("Authorization") token: String,
    ): Response<GetCartItemsResponse>

    @DELETE("/cart/delete/{id}")
    suspend fun deleteCartItem(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteCartResponse>
}