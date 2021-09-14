package com.example.canine_care.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.canine_care.R
import com.example.canine_care.entity.Cart
import com.example.canine_care.repository.CartRepo
import com.example.canine_care.ui.PaymentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartAdapter(private val listProduct: ArrayList<Cart>, val context: Context)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val productname: TextView = view.findViewById(R.id.productname)
        val productdesc: TextView = view.findViewById(R.id.productdesc)
        val productprice: TextView = view.findViewById(R.id.productprice)
        val productdelete: Button = view.findViewById(R.id.productdelete)
        val btnbuy: Button = view.findViewById(R.id.btnbuy)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_layout, parent, false)
        return CartViewHolder(view)

    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = listProduct[position]
        holder.productname.text = cart.pname
        holder.productdesc.text = cart.desc
        holder.productprice.text = cart.price.toString()

//        Glide.with(context)
//            .load(cart.photo)
//            .fitCenter()
//            .into(holder.productimage)
        holder.productdelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${cart.pname} ?")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val cartRepo = CartRepo()
                        val response = cartRepo.deleteCartItem(cart._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "${cart.pname} deleted from Cart!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                listProduct.remove(cart)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        holder.btnbuy.setOnClickListener {
        val intent = Intent(context, PaymentActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}