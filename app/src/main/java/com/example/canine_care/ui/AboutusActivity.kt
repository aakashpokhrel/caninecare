package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.canine_care.R

class AboutusActivity : AppCompatActivity() {
    private lateinit var about: TextView
    private lateinit var maplocation: TextView
    private lateinit var feedbacks: TextView
    private lateinit var theme: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)
        about = findViewById(R.id.about)
        maplocation = findViewById(R.id.maplocation)
        theme = findViewById(R.id.theme)

        about.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        maplocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        theme.setOnClickListener {
            val intent = Intent(this, ThemeActivity::class.java)
            startActivity(intent)
        }
    }
}