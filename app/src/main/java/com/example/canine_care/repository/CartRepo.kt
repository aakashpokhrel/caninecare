package com.example.canine_care.repository

import com.example.canine_care.api.CartAPI
import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Cart
import com.example.canine_care.response.AddCartResponse
import com.example.canine_care.response.DeleteCartResponse
import com.example.canine_care.response.GetCartItemsResponse

class CartRepo: MyApiRequest() {

    private val cartAPI= ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addItemToCart(cart: Cart): AddCartResponse {
        return apiRequest {
            cartAPI.addItemToCart(
                ServiceBuilder.token!!, cart
            )
        }
    }

    suspend fun getCartItems(): GetCartItemsResponse {
        return apiRequest {
            cartAPI.getCartItems(ServiceBuilder.token!!)
        }
    }

    suspend fun  deleteCartItem(id: String): DeleteCartResponse {
        return apiRequest {
            cartAPI.deleteCartItem(ServiceBuilder.token!!,id)
        }
    }
}