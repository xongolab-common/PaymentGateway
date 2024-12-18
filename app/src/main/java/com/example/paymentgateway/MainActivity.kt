package com.example.paymentgateway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentgateway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

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

        binding.btnPayPal.setOnClickListener {
            payPal()
        }
    }

    private fun payPal(){

    }

}