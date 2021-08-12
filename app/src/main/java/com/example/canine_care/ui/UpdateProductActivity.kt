package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.canine_care.R
import com.example.canine_care.entity.Product
import com.example.canine_care.repository.ProductRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProductActivity : AppCompatActivity() {
    private lateinit var etPname: TextInputEditText
    private lateinit var etDesc: TextInputEditText
    private lateinit var etPrice: TextInputEditText
    private lateinit var img: ImageView
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        etPname = findViewById(R.id.etPname)
        etDesc = findViewById(R.id.etDesc)
        etPrice = findViewById(R.id.etPrice)
        btnUpdate = findViewById(R.id.btnUpdate)
//        img = findViewById(R.id.img)

        val intent = intent.getParcelableExtra<Product>("product")
        if (intent != null) {
            etPname.setText(intent.Pname)
            etDesc.setText(intent.Desc)
            etPrice.setText(intent.Price.toString())


//            btnUpdate.setOnClickListener(this)
            btnUpdate.setOnClickListener {
//                val intent = intent(this@)
//                startActivity(intent)
                updateProduct()
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

    private fun updateProduct() {
        val intent = intent.getParcelableExtra<Product>("product")
        val Pname = etPname.text.toString()
        val Desc = etDesc.text.toString()
        val Price = etPrice.text.toString().toInt()

        val product = Product(Pname = Pname, Desc = Desc, Price = Price)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ProductRepository()
                val response = productRepository.updateProduct(intent?._id!!, product)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@UpdateProductActivity,
                            "Product updated", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@UpdateProductActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}