package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canine_care.R
import com.example.canine_care.adapter.ProductAdapter
import com.example.canine_care.entity.Product
import com.example.canine_care.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewProductActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)
        recyclerview = findViewById(R.id.recyclerview)
        getAllProduct()
    }

    private fun getAllProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ProductRepository()
                val response = productRepository.getAllProduct()
                if(response.success == true){
                    //Product details in listProduct
                    val lstProducts = response.data
                    withContext(Dispatchers.Main){
                        val adapter = ProductAdapter(lstProducts as ArrayList<Product>, this@ViewProductActivity)
                        recyclerview.layoutManager = LinearLayoutManager(this@ViewProductActivity)
                        recyclerview.adapter = adapter
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ViewProductActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}