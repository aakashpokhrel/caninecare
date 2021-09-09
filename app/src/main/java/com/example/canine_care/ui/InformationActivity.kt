package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {
    private lateinit var imdiseases: ImageView
    private lateinit var imvaccine:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R .layout.activity_information)
        imdiseases = findViewById(R.id.imdiseases)
        imvaccine = findViewById(R.id.imvaccines)


        imdiseases.setOnClickListener {
            val intent = Intent(this@InformationActivity, DiseaseActivity::class.java)
            startActivity(intent)
        }

        imvaccine.setOnClickListener {
            val intent = Intent(this@InformationActivity, VaccineActivity::class.java)
            startActivity(intent)
        }
    }


}