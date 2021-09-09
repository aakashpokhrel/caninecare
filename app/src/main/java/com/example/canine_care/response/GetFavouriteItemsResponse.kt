package com.example.canine_care.response

import com.example.canine_care.entity.Cart
import com.example.canine_care.entity.Favourite

class GetFavouriteItemsResponse (
    val success: Boolean? = null,
    val count: Int? =null,
    val data: MutableList<Favourite>? = null
)