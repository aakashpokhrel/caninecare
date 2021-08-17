package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.canine_care.R

class RoleActivity : AppCompatActivity() {

    private lateinit var btnAdmin: Button
    private lateinit var btnUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_role_activity`)

        btnAdmin = findViewById(R.id.btnAdmin)
        btnUser = findViewById(R.id.btnUser)



        btnAdmin.setOnClickListener {
            val intent = Intent(this@RoleActivity, AdminActivity::class.java)
            startActivity(intent)
        }
        btnUser.setOnClickListener {
            val intent1 = Intent(this@RoleActivity, MainActivity::class.java)
            startActivity(intent1)
        }
    }
}