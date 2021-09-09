package com.example.canine_care.response

import com.example.canine_care.entity.Cart

data class AddCartResponse (
    val success: Boolean? = null,
    val data: Cart? = null
)