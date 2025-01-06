package com.example.paymentgateway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentgateway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Card Number : 4111 1111 1111 1111
        // dd/mm : 09/2025 or 09/25
        // cvv : 123
        binding.btnMoyasar.setOnClickListener {
            startActivity(Intent(this, MoyasarPaymentActivity::class.java))
        }

        binding.btnRazorpay.setOnClickListener {
            startActivity(Intent(this, RazorpayPaymentActivity::class.java))
        }

        binding.btnPayPal.setOnClickListener {
          //  startPayPalPayment()

        }
    }

  /*  private fun startPayPalPayment() {
        PayPalCheckout.startCheckout(
            createOrder = { createOrderActions: CreateOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(userAction = "PAY_NOW"),
                    purchaseUnits = listOf(
                        PurchaseUnit(
                            amount = com.paypal.checkout.order.Amount(
                                currencyCode = CurrencyCode.USD,
                                value = "10.00"
                            )
                        )
                    )
                )
                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture {
                    // Handle payment success
                }
            },
            onCancel = OnCancel {
                // Handle cancellation
            },
            onError = OnError { errorInfo ->
                // Handle errors
            }
        )
    }*/

}