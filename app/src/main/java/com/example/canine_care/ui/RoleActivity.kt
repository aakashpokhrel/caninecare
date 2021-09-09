package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.canine_care.R

class RoleActivity : AppCompatActivity() {

    private lateinit var btnAdmin: Button
    private lateinit var btnUser: Button
    private lateinit var aboutapp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_role_activity`)

        btnAdmin = findViewById(R.id.btnAdmin)
        btnUser = findViewById(R.id.btnUser)
        aboutapp = findViewById(R.id.aboutapp)



        btnAdmin.setOnClickListener {
            val intent = Intent(this@RoleActivity, AdminActivity::class.java)
            startActivity(intent)
        }
        btnUser.setOnClickListener {
            val intent1 = Intent(this@RoleActivity, MainActivity::class.java)
            startActivity(intent1)
        }
        aboutapp.setOnClickListener {
            val intent = Intent(this@RoleActivity, AboutusActivity::class.java)
            startActivity(intent)
        }
    }
}