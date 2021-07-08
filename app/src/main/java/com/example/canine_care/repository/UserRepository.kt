package com.example.canine_care.repository

import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.User
import com.example.canine_care.response.LoginResponse

class UserRepository: MyApiRequest() {
    private val UserAPI = ServiceBuilder.buildService(com.example.canine_care.api.UserAPI::class.java)

    //Register User
    suspend fun registerUser(user : User) : LoginResponse {
        return apiRequest {
            UserAPI.registerUser(user)
        }
    }
    suspend fun loginUser(username : String, password : String): LoginResponse {
        return apiRequest {
            UserAPI.checkUser(username, password)
        }
    }
}