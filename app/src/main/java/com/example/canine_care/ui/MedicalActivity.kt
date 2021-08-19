package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R

class MedicalActivity : AppCompatActivity() {
    private lateinit var iminformation: ImageView
    private lateinit var imappointment: ImageView
    private lateinit var imlocation: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)
        iminformation = findViewById(R.id.iminformation)
        imappointment = findViewById(R.id.imappointment)
        imlocation = findViewById(R.id.imlocation)


        iminformation.setOnClickListener {
            val intent = Intent(this@MedicalActivity, InformationActivity::class.java)
            startActivity(intent)
        }
        imappointment.setOnClickListener {
            val intent = Intent(this@MedicalActivity, AppointmentActivity::class.java)
            startActivity(intent)
        }

        imlocation.setOnClickListener {
            val intent = Intent(this@MedicalActivity, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}