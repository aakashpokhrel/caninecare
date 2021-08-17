package com.example.canine_care.adapter

import android.app.AlertDialog
import android.content.Context
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
import com.example.canine_care.entity.Pet
import com.example.canine_care.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetAdapter (
    val listPet : MutableList<Pet>,
    val context: Context
): RecyclerView.Adapter<PetAdapter.ShowViewHolder>(){
    class ShowViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvPetName : TextView
        val tvPetAge : TextView
        val tvPetPiece : TextView
        val tvPetDesc : TextView
        val tvPetPrice : TextView
        val image : ImageView
        val delete : ImageView
        val update : ImageView
        init {
            tvPetName= view.findViewById(R.id.tvPetName)
            tvPetAge= view.findViewById(R.id.tvPetAge)
            tvPetPiece= view.findViewById(R.id.tvPetPiece)
            tvPetDesc= view.findViewById(R.id.tvPetDesc)
            tvPetPrice= view.findViewById(R.id.tvPetPrice)
            image = view.findViewById(R.id.stdimage)
            delete = view.findViewById(R.id.delete)
            update = view.findViewById(R.id.update)
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetAdapter.ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_layout_pet, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetAdapter.ShowViewHolder, position: Int) {
        val prdlst = listPet[position]
        holder.tvPetName.text = prdlst.petname
        holder.tvPetAge.text = prdlst.petage.toString()
        holder.tvPetPiece.text = prdlst.petpiece.toString()
        holder.tvPetDesc.text = prdlst.petdesc.toString()
        holder.tvPetPrice.text = prdlst.petprice.toString()
        val imagePath = ServiceBuilder.loadImagePath() + prdlst.photo
        if (!prdlst.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.image)
        }
//        holder.update.setOnClickListener{
//            val intent = Intent(context, UpdateProductActivity::class.java)
//            intent.putExtra("product", prdlst)
//            context.startActivity(intent)
//        }
        holder.delete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete pet")
            builder.setMessage("Are you sure you want to delete ${prdlst.petname} ??")
            builder.setIcon(android.R.drawable.ic_delete)

            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val petRepository = PetRepository()
                        val response = petRepository.deletePet(prdlst._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Pet Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                listPet.remove(prdlst)
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
        return listPet.size
    }
}