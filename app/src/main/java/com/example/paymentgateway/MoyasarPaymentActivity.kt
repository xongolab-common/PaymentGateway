package com.example.paymentgateway


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.moyasar.android.sdk.core.data.response.PaymentResponse
import com.moyasar.android.sdk.core.domain.entities.PaymentResult
import com.moyasar.android.sdk.creditcard.data.models.request.PaymentRequest
import com.moyasar.android.sdk.creditcard.presentation.view.fragments.PaymentFragment


class MoyasarPaymentActivity : AppCompatActivity() {
    private lateinit var paymentFragment: PaymentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moyasar_payment)


        val paymentConfig = PaymentRequest(
            amount = 500,
            currency = "SAR",
            description = "Add wallet",
          //  apiKey = "pk_test_tx7hmREDYpzQEEoN5bjjXoK1LSaw6TqRfyYRfKRo",  // Live
            apiKey = "pk_test_vcFUHJDBwiyRu4Bd3hFuPpTnRPY4gp2ssYdNJMY3",    // Dummy
            baseUrl = "https://api.moyasar.com",
            manual = false,

            createSaveOnlyToken = false
        )

        paymentFragment = PaymentFragment.newInstance(application, paymentConfig) {
            handlePaymentResult(it)
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.paymentSheetFragment, paymentFragment as Fragment)
            commit()
        }
    }

    private fun handlePaymentResult(result: PaymentResult) {
        when (result) {
            is PaymentResult.Completed -> handleCompletedPayment(result.payment)
            is PaymentResult.Failed -> {
                Toast.makeText(this, result.error.message, Toast.LENGTH_SHORT).show()
                finish()
            }
            PaymentResult.Canceled -> {
                Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_SHORT).show()
                finish()
            }
            else -> {
                Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun handleCompletedPayment(payment: PaymentResponse) {
        when (payment.status) {
            "paid" -> {
                val resultIntent = Intent().apply {
                    putExtra("paymentId", payment.id)
                    putExtra("paymentResponse", payment.toString())
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            "failed" -> {
                val errorMessage = payment.source["message"] ?: "Payment Failed"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                finish()
            }
            else -> {
                Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
