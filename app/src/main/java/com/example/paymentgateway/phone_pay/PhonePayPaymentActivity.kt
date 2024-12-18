package com.example.paymentgateway.phone_pay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentgateway.R
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Register : https://www.phonepe.com/business-solutions/payment-gateway/register/?utm_source=unable_to_register
// Login : https://business.phonepe.com/login
// Doc : https://developer.phonepe.com/docs/registration-and-login-1/


class PhonePayPaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_pay_payment)

        initiatePayment()
    }

    private fun initiatePayment() {
        val requestBody = mapOf(
            "merchantId" to "YOUR_MERCHANT_ID",
            "transactionId" to "TXN${System.currentTimeMillis()}",
            "amount" to 10000,  // Amount in paisa (₹100)
            "merchantOrderId" to "ORDER${System.currentTimeMillis()}",
            "redirectUrl" to "https://your_redirect_url.com",
            "callbackUrl" to "https://your_callback_url.com"
        )

        val call = RetrofitClient.apiService.initiatePayment(requestBody)

        call.enqueue(object : Callback<PaymentResponse> {
            override fun onResponse(
                call: Call<PaymentResponse>,
                response: Response<PaymentResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@PhonePayPaymentActivity,
                        "Payment Success: ${response.body()?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@PhonePayPaymentActivity,
                        "Payment Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                Toast.makeText(
                    this@PhonePayPaymentActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}