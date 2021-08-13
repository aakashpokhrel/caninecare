package com.example.canine_care.response

import com.example.canine_care.entity.User

class GetUserProfileResponse (
   val success: Boolean? = null,
   val data: User? = null,
   val id:String? = null
)