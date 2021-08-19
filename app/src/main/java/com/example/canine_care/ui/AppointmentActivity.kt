package com.example.canine_care.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.example.canine_care.R
import com.example.canine_care.entity.Appointment
import com.example.canine_care.entity.Product
import com.example.canine_care.repository.AppointmentRepository
import com.example.canine_care.repository.PetRepository
import com.example.canine_care.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AppointmentActivity : AppCompatActivity() {
    private lateinit var etfname: EditText
    private lateinit var etmobileno: EditText
    private lateinit var etemailadd: EditText
    private lateinit var etDate: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnBook: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        etfname = findViewById(R.id.etfname)
        etmobileno = findViewById(R.id.etmobileno)
        etemailadd = findViewById(R.id.etemailadd)
        etDate = findViewById(R.id.etDate)
        etAddress = findViewById(R.id.etAddress)
        img = findViewById(R.id.img)
        btnBook = findViewById(R.id.btnBook)


        btnBook.setOnClickListener{
            saveBook()
        }
        img.setOnClickListener{
            loadPopUpMenu()
        }
    }

    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this@AppointmentActivity, img)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }

    private fun saveBook() {
        val fullname = etfname.text.toString()
        val mnumber = etmobileno.text.toString()
        val email = etemailadd.text.toString()
        val apdate = etDate.text.toString()
        val apaddress = etAddress.text.toString()

        val appointment = Appointment(fullname = fullname, mnumber = mnumber, email = email,apdate = apdate, apaddress = apaddress)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val appointmentRepository = AppointmentRepository()
                val response = appointmentRepository.addAppointment(appointment)
                if(response.success == true){
                    if(imageUrl != null){
                        uploadImage(response.data!!._id!!)
                    }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AppointmentActivity,
                            "Product added", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AppointmentActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                img.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                img.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }
    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
    private fun uploadImage(petId: String) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("file", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val petRepository = PetRepository()
                    val response = petRepository.uploadImage(petId, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AppointmentActivity, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                            this@AppointmentActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}