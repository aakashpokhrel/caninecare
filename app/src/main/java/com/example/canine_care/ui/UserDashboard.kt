package com.example.canine_care.ui

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.canine_care.R
import kotlinx.android.synthetic.main.activity_user_dashboard.*

class UserDashboard : AppCompatActivity() {
    private lateinit var improfile: ImageView
    private lateinit var imhome: ImageView
    private lateinit var improduct: ImageView
    private lateinit var imcart: ImageView
    private lateinit var imadopt: ImageView
    private lateinit var immedical: ImageView
    private lateinit var imfavourite: ImageView
    private lateinit var imaboutus: ImageView
    private lateinit var imfeedback: ImageView
    private lateinit var imlogout: ImageView


    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)

        improduct = findViewById(R.id.improduct)
        imcart = findViewById(R.id.imcart)
        imadopt = findViewById(R.id.imadopt)
        immedical = findViewById(R.id.immedical)
        imaboutus = findViewById(R.id.imaboutus)
        imfavourite = findViewById(R.id.imfavourite)





//        improfile.setOnClickListener {
//            val intent = Intent(this@UserDashboard, ProfileActivity::class.java)
//            startActivity(intent)
//        }

//        imhome.setOnClickListener {
////            val intent = Intent(this@UserDashboard, AddProductActivity::class.java)
////            startActivity(intent)
//        }

        improduct.setOnClickListener {
            val intent = Intent(this@UserDashboard, ViewProductActivity::class.java)
            startActivity(intent)
        }

//        impayment.setOnClickListener {
//            val intent = Intent(this@UserDashboard, PaymentActivity::class.java)
//            startActivity(intent)
//        }
        imcart.setOnClickListener {
           val intent = Intent(this@UserDashboard, CartActivity::class.java)
            startActivity(intent)
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

        imfavourite.setOnClickListener{
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }

                toggle = ActionBarDrawerToggle(this, drawyerLayout, R.string.open, R.string.close)

        drawyerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.menuAbout->{
                    startActivity(Intent(this, AboutusActivity::class.java))
                }
                R.id.home->{
                    startActivity(Intent(this, UserDashboard::class.java))
                }
                R.id.menuCart->{
                    startActivity(Intent(this, CartActivity::class.java))
                }
                R.id.Menufavourite->{
                    startActivity(Intent(this, FavouriteActivity::class.java))
                }
                R.id.menuFeedback->{
                    startActivity(Intent(this, FeedbackActivity::class.java))
                }
                R.id.menuLogout->{
                    startActivity(Intent(this, MainActivity::class.java))
                }

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