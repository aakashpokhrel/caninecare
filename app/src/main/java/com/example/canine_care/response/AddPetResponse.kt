package com.example.canine_care.response

import com.example.canine_care.entity.Pet


class AddPetResponse (
    val success: Boolean? = null,
    val data: Pet? = null
)