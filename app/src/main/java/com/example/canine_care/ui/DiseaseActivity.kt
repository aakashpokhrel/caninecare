package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R

class DiseaseActivity : AppCompatActivity() {
    private lateinit var imdog:ImageView
    private lateinit var imcat:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease)

        imdog = findViewById(R.id.imdog)
        imcat = findViewById(R.id.imcat)

        imdog.setOnClickListener {
            val intent = Intent(this@DiseaseActivity, DogdiseasesActivity::class.java)
            startActivity(intent)
        }
        imcat.setOnClickListener {
            val intent = Intent(this@DiseaseActivity, catdiseaseActivity::class.java)
            startActivity(intent)
        }
    }
}