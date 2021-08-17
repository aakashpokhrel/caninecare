package com.example.canine_care.repository

import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.PetAPI

import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Pet
import com.example.canine_care.response.AddPetResponse
import com.example.canine_care.response.DeletePetResponse
import com.example.canine_care.response.GetAllPetResponse


import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody

class PetRepository: MyApiRequest() {
    private  val petAPI =
        ServiceBuilder.buildService(PetAPI::class.java)

    //Add Pet
    suspend fun addPet(pet : Pet): AddPetResponse {
        return apiRequest {
            petAPI.addPet(
                ServiceBuilder.token!!,pet
            )
        }
    }
    //View Pets
    suspend fun getAllPet(): GetAllPetResponse {
        return apiRequest {
            petAPI.getAllPet(
                ServiceBuilder.token!!
            )
        }
    }
    //Delete Pet
    suspend fun deletePet(id :String): DeletePetResponse {
        return apiRequest {
            petAPI.deletePet(
                ServiceBuilder.token!!,id
            )
        }
    }
    //Update Pet
    suspend fun updatePet(id :String,pet: Pet): DeletePetResponse {
        return apiRequest {
            petAPI.updatePet(
                ServiceBuilder.token!!, id, pet
            )
        }
    }
    //upload image
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            petAPI.uploadImage(
                ServiceBuilder.token!!, id, body
            )
        }
    }
}