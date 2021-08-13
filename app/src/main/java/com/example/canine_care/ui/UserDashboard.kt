package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R

class UserDashboard : AppCompatActivity() {
    private lateinit var improfile: ImageView
    private lateinit var imhome: ImageView
    private lateinit var improduct: ImageView
    private lateinit var imcart: ImageView
    private lateinit var imadopt: ImageView
    private lateinit var immedical: ImageView
    private lateinit var impayment: ImageView
    private lateinit var imaboutus: ImageView
    private lateinit var imfeedback: ImageView
    private lateinit var imlogout: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)
        improfile = findViewById(R.id.improfile)
        imhome = findViewById(R.id.imhome)
        improduct = findViewById(R.id.improduct)
        imcart = findViewById(R.id.imcart)
        imadopt = findViewById(R.id.imadopt)
        immedical = findViewById(R.id.immedical)
        impayment = findViewById(R.id.impayment)
        imaboutus = findViewById(R.id.imaboutus)
        imfeedback = findViewById(R.id.imfeedback)
        imlogout = findViewById(R.id.imlogout)

        improduct.setOnClickListener {
            val intent = Intent(this@UserDashboard, ViewProductActivity::class.java)
            startActivity(intent)
        }

        imhome.setOnClickListener {
            val intent = Intent(this@UserDashboard, AddProductActivity::class.java)
            startActivity(intent)
        }
        imcart.setOnClickListener {
            val intent = Intent(this@UserDashboard, UpdateProductActivity::class.java)
            startActivity(intent)
        }
        improfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        immedical.setOnClickListener{
            val intent = Intent(this, MedicalActivity::class.java)
            startActivity(intent)
        }

        imaboutus.setOnClickListener{
            val intent = Intent(this, AboutusActivity::class.java)
            startActivity(intent)
        }

        imfeedback.setOnClickListener{
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
    }
}