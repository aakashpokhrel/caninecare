package com.example.canine_care.response


import com.example.canine_care.entity.Favourite


data class AddFavouriteResponse (
    val success: Boolean? = null,
    val data: Favourite? = null
)