package com.example.canine_care.response

import com.example.canine_care.entity.Cart

data class GetCartItemsResponse (
    val success: Boolean? = null,
    val count: Int? =null,
    val data: MutableList<Cart>? = null
)