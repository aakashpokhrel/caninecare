package com.example.canine_care.response

import com.example.canine_care.entity.User

data class UpdateUserResponse(
    val success: Boolean? = null,
    val data: User? = null
)