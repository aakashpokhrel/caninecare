package com.example.canine_care.repository

import com.example.canine_care.api.CartAPI
import com.example.canine_care.api.FavouriteAPI
import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Cart
import com.example.canine_care.entity.Favourite
import com.example.canine_care.response.*

class FavouriteRepo:MyApiRequest() {
    private val FavouriteAPI= ServiceBuilder.buildService(FavouriteAPI::class.java)

    suspend fun addItemToFavourite(favourite: Favourite): AddFavouriteResponse {
       return apiRequest {
           FavouriteAPI.addItemToFavourite(
               ServiceBuilder.token!!, favourite
           )
       }
       }

    suspend fun getFavouriteItems(): GetFavouriteItemsResponse {
        return apiRequest {
            FavouriteAPI.getFavouriteItems(ServiceBuilder.token!!)
        }
    }

    suspend fun  deleteCartItem(id: String): DeleteFavouriteResponse {
        return apiRequest {
            FavouriteAPI.deleteFavouriteItem(ServiceBuilder.token!!,id)
        }
    }
}