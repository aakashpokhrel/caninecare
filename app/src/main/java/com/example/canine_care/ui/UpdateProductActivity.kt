package com.example.canine_care.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.canine_care.R
import com.example.canine_care.entity.Product
import com.example.canine_care.repository.ProductRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UpdateProductActivity : AppCompatActivity() {
    private lateinit var etpname: TextInputEditText
    private lateinit var etdescription: TextInputEditText
    private lateinit var etprice: TextInputEditText
    private lateinit var img: ImageView
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        etpname = findViewById(R.id.etpname)
        etdescription = findViewById(R.id.etdescription)
        etprice = findViewById(R.id.etprice)
        btnUpdate = findViewById(R.id.btnUpdate)
//        img = findViewById(R.id.img)

        val intent = intent.getParcelableExtra<Product>("product")
        if (intent != null) {
            etpname.setText(intent.pname)
            etdescription.setText(intent.description.toString())
            etprice.setText(intent.price.toString())


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
//                    updateProduct()
//                }
//            }
//        }


    }

    private fun updateProduct() {
        val intent = intent.getParcelableExtra<Product>("product")
        val pname = etpname.text.toString()
        val description = etdescription.text.toString().toInt()
        val price = etprice.text.toString().toInt()

        val product = Product(pname = pname, description = description, price = price)
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



