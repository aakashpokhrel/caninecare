package com.example.canine_care.response

import com.example.canine_care.entity.Product

class GetAllProductResponse (
    val success : Boolean? = null,
    val count: Int? = null,
    val data : MutableList<Product>? = null
        )