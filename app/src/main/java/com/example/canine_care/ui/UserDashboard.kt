package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.canine_care.R
import kotlinx.android.synthetic.main.activity_user_dashboard.*

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

    lateinit var toggle: ActionBarDrawerToggle


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




        improfile.setOnClickListener {
            val intent = Intent(this@UserDashboard, ProfileActivity::class.java)
            startActivity(intent)
        }

        imhome.setOnClickListener {
//            val intent = Intent(this@UserDashboard, AddProductActivity::class.java)
//            startActivity(intent)
        }

        improduct.setOnClickListener {
            val intent = Intent(this@UserDashboard, ViewProductActivity::class.java)
            startActivity(intent)
        }
        imcart.setOnClickListener {
//            val intent = Intent(this@UserDashboard, UpdateProductActivity::class.java)
//            startActivity(intent)
        }
        imadopt.setOnClickListener {
            val intent = Intent(this@UserDashboard,ViewPetActivity::class.java)
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

                toggle = ActionBarDrawerToggle(this, drawyerLayout, R.string.open, R.string.close)

        drawyerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.imhome -> Toast.makeText(applicationContext, "clicked home", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}