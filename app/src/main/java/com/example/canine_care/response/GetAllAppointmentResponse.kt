package com.example.canine_care.response

import com.example.canine_care.entity.Appointment

class GetAllAppointmentResponse (
    val success : Boolean? = null,
    val count: Int? = null,
    val data : MutableList<Appointment>? = null
        )