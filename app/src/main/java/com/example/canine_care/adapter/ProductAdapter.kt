package com.example.canine_care.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.canine_care.R
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Product
import com.example.canine_care.repository.ProductRepository
import com.example.canine_care.ui.UpdateProductActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductAdapter (
    val listProduct : MutableList<Product>,
    val context: Context): RecyclerView.Adapter<ProductAdapter.ShowViewHolder>(){
    class ShowViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvPname : TextView
        val tvDesc : TextView
        val tvPrice : TextView
        val image : ImageView
        val delete : ImageView
        val update : ImageView
        init {
            tvPname= view.findViewById(R.id.tvPname)
            tvDesc= view.findViewById(R.id.tvDesc)
            tvPrice= view.findViewById(R.id.tvPrice)
            image = view.findViewById(R.id.stdimg)
            delete = view.findViewById(R.id.delete)
            update = view.findViewById(R.id.update)
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_layout_product, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ShowViewHolder, position: Int) {
        val prdlst = listProduct[position]
        holder.tvPname.text = prdlst.Pname
        holder.tvDesc.text = prdlst.Desc.toString()
        holder.tvPrice.text = prdlst.Price.toString()
        val imagePath = ServiceBuilder.loadImagePath() + prdlst.photo
        if (!prdlst.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.image)
        }
        holder.update.setOnClickListener{
            val intent = Intent(context, UpdateProductActivity::class.java)
            intent.putExtra("product", prdlst)
            context.startActivity(intent)
        }
        holder.delete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete product")
            builder.setMessage("Are you sure you want to delete ${prdlst.Pname} ??")
            builder.setIcon(android.R.drawable.ic_delete)

            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val productRepository = ProductRepository()
                        val response = productRepository.deleteProduct(prdlst._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Product Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                listProduct.remove(prdlst)
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
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}