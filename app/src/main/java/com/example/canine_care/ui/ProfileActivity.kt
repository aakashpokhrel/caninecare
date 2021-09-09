package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.canine_care.R
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.User
import com.example.canine_care.repository.UserRepository
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    private lateinit var firstname:EditText
    private lateinit var lastname:EditText
    private lateinit var name: EditText
    private lateinit var email: EditText

    private lateinit var profileupdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firstname = findViewById(R.id.Profilefirstname)
        lastname = findViewById(R.id.ProfileLastname)
        name = findViewById(R.id.Profilename)
        email = findViewById(R.id.Profileemail)

        profileupdate = findViewById(R.id.btnprofileupdate)
        loadUserDetails()
        btnprofileupdate.setOnClickListener {
            updateprofile()
        }

    }


    private fun loadUserDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = userRepo.getMe()

                if (response.success == true) {

//                    Log.d("User Id",fName.toString())
                    withContext(Dispatchers.Main) {
                        firstname.setText(response.data?.fname)
                        lastname.setText(response.data?.lname)
                        name.setText(response.data?.username)
                        email.setText(response.data?.email)


                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun updateprofile() {
        val firstname = firstname.text.toString()
        val lastname = lastname.text.toString()
        val username = name.text.toString()
        val email =  email.text.toString()


        val user =
            User(fname = firstname, lname = lastname, username = username, email = email)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepository()
                val response = ServiceBuilder.id?.let { userRepo.updateUser(it, user) }
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Success", Toast.LENGTH_SHORT
                        ).show()


                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }




}


