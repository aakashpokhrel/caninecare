package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canine_care.R
import com.example.canine_care.adapter.AppointmentAdapter
import com.example.canine_care.entity.Appointment
import com.example.canine_care.repository.AppointmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewAppointmentActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appointment)

        recyclerview = findViewById(R.id.recyclerview)
        getAllAppointment()
    }

    private fun getAllAppointment() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.getAllAppointment()
                if(response.success == true){
                    //Appointment details in listlstAppointment
                    val lstAppointments = response.data
                    withContext(Dispatchers.Main){
                        val adapter = AppointmentAdapter(lstAppointments as ArrayList<Appointment>, this@ViewAppointmentActivity)
                        recyclerview.layoutManager = LinearLayoutManager(this@ViewAppointmentActivity)
                        recyclerview.adapter = adapter
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ViewAppointmentActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
