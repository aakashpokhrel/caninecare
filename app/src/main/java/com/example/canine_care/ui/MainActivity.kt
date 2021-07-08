package com.example.canine_care.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.canine_care.R
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.etusername)
        password = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)
        linearLayout = findViewById(R.id.linearlayout)

        btnLogin.setOnClickListener {
//            val intent = Intent(this@MainActivity, Dashboard::class.java)
//            startActivity(intent)

            login()
        }

        tvSignUp.setOnClickListener {
            val intent1 = Intent( this@MainActivity, RegisterActivity::class.java)
            startActivity(intent1)
        }
    }

    private fun login() {
        val username = username.text.toString()
        val password = password.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.loginUser(username, password)

                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
                    loginSharedPref()
                    startActivity(
                        Intent(
                            this@MainActivity,
//                            Dashboard::class.java
                            User_dashboard::class.java
                        )
                    )
                    finish()

                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    fun loginSharedPref() {
        val username = username.text.toString()
        val password = password.text.toString()
        val loginSharedPref = getSharedPreferences("LoginPref", MODE_PRIVATE)
        val editor = loginSharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.commit()
    }
}