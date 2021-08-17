package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canine_care.R
import com.example.canine_care.adapter.PetAdapter
import com.example.canine_care.entity.Pet
import com.example.canine_care.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewPetActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pet)
        recyclerview = findViewById(R.id.recyclerview)
        getAllPet()
    }

    private fun getAllPet() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val petRepository = PetRepository()
                val response = petRepository.getAllPet()
                if(response.success == true){
                    //Pet details in listPet
                    val lstPets = response.data
                    withContext(Dispatchers.Main){
                        val adapter = PetAdapter(lstPets as ArrayList<Pet>, this@ViewPetActivity)
                        recyclerview.layoutManager = LinearLayoutManager(this@ViewPetActivity)
                        recyclerview.adapter = adapter
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ViewPetActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}