package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.canine_care.Order
import com.example.canine_care.R
import com.example.canine_care.RetrofitInterface
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentActivity : AppCompatActivity(), PaymentResultWithDataListener {
    lateinit var retrofit:Retrofit
    lateinit var retrofitInterface: RetrofitInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        Checkout.preload(applicationContext)

        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitInterface = retrofit.create(RetrofitInterface::class.java)

        findViewById<Button>(R.id.paybutton)
            .setOnClickListener {
                val amountEdit:EditText = findViewById(R.id.amountEdit);
                val amount = amountEdit.text.toString()

                if (amount.isEmpty()){
                    return@setOnClickListener
                }

                getOrderId(amount)
            }
    }

    private fun getOrderId(amount: String) {
        val map = HashMap<String, String>()
        map["amount"] = amount

        retrofitInterface
            .getOrderId(map).enqueue(object : Callback<Order> {
                override fun onFailure(call: Call<Order>, t: Throwable) {
                    Toast.makeText(this@PaymentActivity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    if (response.body() != null)
                        initiatePayment(amount, response.body()!!)
                }
            })

    }

    private fun initiatePayment(amount: String, order: Order) {
        val checkout = Checkout()
        checkout.setKeyID(order.getKeyId())

        checkout.setImage(R.drawable.ic_launcher_background)

        val paymentOptions = JSONObject()
        paymentOptions.put("name","Canine Care")
        paymentOptions.put("amount", amount)
        paymentOptions.put("order_id", order.getOrderId())
        paymentOptions.put("currency", "INR")
        paymentOptions.put("description", "Reference no: #1234")
        checkout.open(this, paymentOptions)

    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        val map = HashMap<String, String>()

        map["order_id"] = p1!!.orderId
        map["pay_id"] = p1.paymentId
        map["signature"] = p1.signature

        retrofitInterface.updateTransaction(map)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@PaymentActivity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if (response.body().equals("success"))
                        Toast.makeText(this@PaymentActivity, "Payment successful", Toast.LENGTH_LONG)
                            .show()
                }

            })
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment failed", Toast.LENGTH_LONG).show()
    }
}
