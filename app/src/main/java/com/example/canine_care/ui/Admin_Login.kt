package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.canine_care.R
import com.google.android.material.textfield.TextInputEditText

class Admin_Login : AppCompatActivity() {
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin__login)
        username = findViewById(R.id.etusername)
        password = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        linearLayout = findViewById(R.id.linearlayout)

      //  btnLogin.setOnClickListener {
     //       val intent = Intent(this@Admin_Login, Admin_dashboard::class.java)
     //       startActivity(intent)

        }
    }
}