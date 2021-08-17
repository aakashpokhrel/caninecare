package com.example.canine_care.response

import com.example.canine_care.entity.Pet


class GetAllPetResponse (
    val success : Boolean? = null,
    val count: Int? = null,
    val data : MutableList<Pet>? = null
)