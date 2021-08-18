package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R

class MedicalActivity : AppCompatActivity() {
    private lateinit var iminformation: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)
        iminformation = findViewById(R.id.iminformation)
        iminformation.setOnClickListener {
            val intent = Intent(this@MedicalActivity, InformationActivity::class.java)
            startActivity(intent)
        }
    }
}