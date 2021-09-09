package com.example.canine_care.api

import com.example.canine_care.entity.Cart
import com.example.canine_care.entity.Favourite
import com.example.canine_care.response.*
import retrofit2.Response
import retrofit2.http.*

interface FavouriteAPI {
    @POST("/favourite/add")
    suspend fun addItemToFavourite(
        @Header("Authorization") token: String,
        @Body favourite: Favourite
    ): Response<AddFavouriteResponse>

    @GET("/favourite/")
    suspend fun getFavouriteItems(
        @Header("Authorization") token: String,
    ): Response<GetFavouriteItemsResponse>

    @DELETE("/favourite/delete/{id}")
    suspend fun deleteFavouriteItem(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteFavouriteResponse>
}