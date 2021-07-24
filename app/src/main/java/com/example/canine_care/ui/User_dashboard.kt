package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.canine_care.R

class User_dashboard : AppCompatActivity() {
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
    }
}