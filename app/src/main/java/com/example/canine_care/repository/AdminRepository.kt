package com.example.canine_care.repository

import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Admin
import com.example.canine_care.response.LoginResponse

class AdminRepository: MyApiRequest() {
    private val AdminAPI = ServiceBuilder.buildService(com.example.canine_care.api.AdminAPI::class.java)

    //Register Admin
    suspend fun registerAdmin(admin : Admin) : LoginResponse {
        return apiRequest {
            AdminAPI.registerAdmin(admin)
        }
    }
    suspend fun loginAdmin(username : String, password : String): LoginResponse {
        return apiRequest {
            AdminAPI.checkAdmin(username, password)
        }
    }
}