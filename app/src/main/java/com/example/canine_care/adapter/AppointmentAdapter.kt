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
import com.example.canine_care.entity.Appointment
import com.example.canine_care.repository.AppointmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppointmentAdapter (
    val listAppointment : MutableList<Appointment>,
    val context: Context
): RecyclerView.Adapter<AppointmentAdapter.ShowViewHolder>(){
    class ShowViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvfullname : TextView
        val tvmnumber : TextView
        val tvapaddress : TextView
        val tvemail : TextView
        val tvapdate : TextView
        val image : ImageView
        val delete : ImageView
        val update : ImageView
        init {
            tvfullname= view.findViewById(R.id.tvfullname)
            tvmnumber= view.findViewById(R.id.tvmnumber)
            tvemail= view.findViewById(R.id.tvemail)
            tvapdate= view.findViewById(R.id.tvapdate)
            tvapaddress= view.findViewById(R.id.tvapaddress)
            image = view.findViewById(R.id.stdimage)
            delete = view.findViewById(R.id.delete)
            update = view.findViewById(R.id.update)
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppointmentAdapter.ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_layout_appointment, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentAdapter.ShowViewHolder, position: Int) {
        val aptlst = listAppointment[position]
        holder.tvfullname.text = aptlst.fullname
        holder.tvmnumber.text = aptlst.mnumber.toString()
        holder.tvemail.text = aptlst.email.toString()
        holder.tvapdate.text = aptlst.apdate.toString()
        holder.tvapaddress.text = aptlst.apaddress.toString()
        val imagePath = ServiceBuilder.loadImagePath() + aptlst.photo
        if (!aptlst.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.image)
        }
//        holder.update.setOnClickListener{
//            val intent = Intent(context, UpdateProductActivity::class.java)
//            intent.putExtra("product", aptlst)
//            context.startActivity(intent)
//        }
        holder.delete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete appointment")
            builder.setMessage("Are you sure you want to delete ${aptlst.fullname} ??")
            builder.setIcon(android.R.drawable.ic_delete)

            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val appointmentRepository = AppointmentRepository()
                        val response = appointmentRepository.deleteAppointment(aptlst._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Appointment Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                listAppointment.remove(aptlst)
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
        return listAppointment.size
    }
}