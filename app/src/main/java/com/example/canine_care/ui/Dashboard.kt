package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.canine_care.R

class Dashboard : AppCompatActivity() {



    private lateinit var btnAddProduct: CardView
    private lateinit var btnUpdateProduct: CardView
    private lateinit var btnAddPet : CardView
    private lateinit var btnViewAppointment : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        btnUpdateProduct = findViewById(R.id.btnUpdateProduct)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnAddPet = findViewById(R.id.btnAddPet)
        btnViewAppointment = findViewById(R.id.btnViewAppointment)

        btnAddProduct.setOnClickListener {
            val intent = Intent(
                this, AddProductActivity::class.java
            )
            startActivity(intent)
        }

        btnViewAppointment.setOnClickListener {
            val intent = Intent(
                this, ViewAppointmentActivity::class.java
            )
            startActivity(intent)
        }

        btnAddPet.setOnClickListener {
            val intent = Intent(
                this, AddPetActivity::class.java
            )
            startActivity(intent)
        }



        btnUpdateProduct.setOnClickListener {
            val intent = Intent(
                this, UpdateProductActivity::class.java
            )
            startActivity(intent)
        }
    }
}