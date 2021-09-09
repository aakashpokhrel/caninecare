package com.example.canine_care.repository

import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.User
import com.example.canine_care.response.GetUserProfileResponse
import com.example.canine_care.response.LoginResponse
import com.example.canine_care.response.UpdateUserResponse

class UserRepository: MyApiRequest() {
    private val UserAPI = ServiceBuilder.buildService(com.example.canine_care.api.UserAPI::class.java)

    //Register User
    suspend fun registerUser(user : User) : LoginResponse {
        return apiRequest {
            UserAPI.registerUser(user)
        }
    }

    //login user
    suspend fun loginUser(username : String, password : String): LoginResponse {
        return apiRequest {
            UserAPI.checkUser(username, password)
        }
    }
  //Get Profile details
    suspend fun getMe(): GetUserProfileResponse {
        return apiRequest {
            UserAPI.getMe(ServiceBuilder.token!!)
        }
    }
   //Update user profile
    suspend fun updateUser(id:String, user: User): UpdateUserResponse {
        return apiRequest {
            UserAPI.updateUser(id, user)
        }
    }
}