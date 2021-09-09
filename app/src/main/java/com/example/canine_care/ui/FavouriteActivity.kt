package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canine_care.R
import com.example.canine_care.adapter.CartAdapter
import com.example.canine_care.adapter.FavouriteAdapter
import com.example.canine_care.entity.Cart
import com.example.canine_care.entity.Favourite
import com.example.canine_care.repository.CartRepo
import com.example.canine_care.repository.FavouriteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteActivity : AppCompatActivity() {
    private lateinit var recyclerViewFavourite:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        recyclerViewFavourite = findViewById(R.id.recyclerViewFavourite)
        loadFavouriteItems()
    }

    private fun loadFavouriteItems() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val favouriteRepo = FavouriteRepo()
                val response = favouriteRepo.getFavouriteItems()
                if (response.success == true){
                    val lstPets = response.data
                    withContext(Dispatchers.Main){
                        val adapter = FavouriteAdapter(lstPets as ArrayList<Favourite>, this@FavouriteActivity)
                        recyclerViewFavourite.layoutManager = LinearLayoutManager(this@FavouriteActivity)
                        recyclerViewFavourite.adapter = adapter
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FavouriteActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}