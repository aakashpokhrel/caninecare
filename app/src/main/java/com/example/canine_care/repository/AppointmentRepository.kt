package com.example.canine_care.repository

import com.example.canine_care.api.AppointmentAPI
import com.example.canine_care.api.MyApiRequest

import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Appointment
import com.example.canine_care.response.AddAppointmentResponse
import com.example.canine_care.response.DeleteAppointmentResponse
import com.example.canine_care.response.GetAllAppointmentResponse
import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody

class AppointmentRepository: MyApiRequest() {
    private  val appointmentAPI =
        ServiceBuilder.buildService(AppointmentAPI::class.java)

    //Add student
    suspend fun addAppointment(appointment : Appointment): AddAppointmentResponse {
        return apiRequest {
            appointmentAPI.addAppointment(
                ServiceBuilder.token!!,appointment
            )
        }
    }
    //View Students
    suspend fun getAllAppointment(): GetAllAppointmentResponse {
        return apiRequest {
            appointmentAPI.getAllAppointment(
                ServiceBuilder.token!!
            )
        }
    }
    //Delete Student
    suspend fun deleteAppointment(id :String): DeleteAppointmentResponse {
        return apiRequest {
            appointmentAPI.deleteAppointment(
                ServiceBuilder.token!!,id
            )
        }
    }
    //Update Student
    suspend fun updateAppointment(id :String,appointment: Appointment): DeleteAppointmentResponse {
        return apiRequest {
            appointmentAPI.updateAppointment(
                ServiceBuilder.token!!, id, appointment
            )
        }
    }
//    upload image
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            appointmentAPI.uploadImage(
                ServiceBuilder.token!!, id, body
            )
        }
    }
}