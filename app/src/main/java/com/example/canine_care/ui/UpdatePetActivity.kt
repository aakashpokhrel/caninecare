package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.canine_care.R
import com.example.canine_care.entity.Pet
import com.example.canine_care.repository.PetRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdatePetActivity : AppCompatActivity() {

    private lateinit var etPetName: TextInputEditText
    private lateinit var etPetAge : TextInputEditText
    private lateinit var etPetPiece: TextInputEditText
    private lateinit var etPetDesc: TextInputEditText
    private lateinit var etPetPrice: TextInputEditText
    //    private lateinit var img: ImageView
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pet)

        etPetName = findViewById(R.id.etPetName)
        etPetAge = findViewById(R.id.etPetAge)
        etPetPiece = findViewById(R.id.etPetPiece)
        etPetDesc = findViewById(R.id.etPetDesc)
        etPetPrice = findViewById(R.id.etPetPrice)
        btnUpdate = findViewById(R.id.btnUpdate)
//        img = findViewById(R.id.img)

        val intent = intent.getParcelableExtra<Pet>("pet")
        if (intent != null) {
            etPetName.setText(intent.petname)
            etPetAge.setText(intent.petage)
            etPetPiece.setText(intent.petpiece)
            etPetDesc.setText(intent.petdesc)
            etPetPrice.setText(intent.petprice.toString())


//            btnUpdate.setOnClickListener(this)
            btnUpdate.setOnClickListener {
//                val intent = intent(this@)
//                startActivity(intent)
                updatePet()
            }
        }


//        override fun onClick(v: View?) {
//            when (v?.id) {
//                R.id.btnUpdate -> {
//                    updateShow()
//                }
//            }
//        }


    }

    private fun updatePet() {
        val intent = intent.getParcelableExtra<Pet>("pet")
        val PetName = etPetName.text.toString()
        val PetAge = etPetAge.text.toString()
        val PetPiece = etPetPiece.text.toString()
        val PetDesc = etPetDesc.text.toString()
        val PetPrice = etPetPrice.text.toString().toInt()

        val pet = Pet(petname = PetName,petage = PetAge,petpiece = PetPiece, petdesc = PetDesc, petprice = PetPrice)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val petRepository = PetRepository()
                val response = petRepository.updatePet(intent?._id!!, pet)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@UpdatePetActivity,
                            "Pet updated", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@UpdatePetActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
