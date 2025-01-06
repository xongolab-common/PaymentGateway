package com.example.paymentgateway

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paymentgateway.databinding.ActivityMainBinding
import com.example.paymentgateway.databinding.ActivityRazorpayPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class RazorpayPaymentActivity : AppCompatActivity(), PaymentResultWithDataListener {

    private lateinit var binding: ActivityRazorpayPaymentBinding
    var amount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRazorpayPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }


    private fun initView(){

        if(intent!=null){
            if(intent.hasExtra("amount")){
                amount = intent.getDoubleExtra("amount",0.0)

                println("initView RazorPay...$amount")
            }
        }
        Log.e("initView", "RazorpayPaymentActivity razorpayAmount==> " + Constants.razorpayAmount)

        Checkout.preload(applicationContext)
        //Razorpay
        startPayment(amount)
    }

    private fun startPayment(amount: Double) {
        /**
         * Instantiate Checkout
         */
        val checkout = Checkout()
        checkout.setKeyID(""+ "rzp_live_ILgsfZCZoFIKMb")  // razorpay_key
        /**
         * Set your logo here
         */
        checkout.setImage(R.mipmap.ic_launcher)
        /**
         * Reference to current activity
         */
        val activity: Activity = this
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            val options = JSONObject()
            options.put("name", ""+resources.getString(R.string.app_name))
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#DA1521")
            options.put("currency", "INR")
            options.put("send_sms_hash", true)
            options.put("amount", amount * 100) //pass amount in currency subunits

            val prefill = JSONObject()
            prefill.put("email", "" + "sweta@gmail.com")
            prefill.put("contact", "" + "91" + "" + "1234567899")
            options.put("prefill", prefill)


            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            checkout.open(activity, options)
        } catch (e: Exception) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e)
        }
    }

    override fun onPaymentSuccess(s: String?, paymentData: PaymentData?) {
        Log.e(
            "TAG", "onPaymentSuccess: \${payment Id} " + paymentData!!.getPaymentId()
        )
        Constants.razorpayAmount = amount
        Constants.razorpaytransactionId = paymentData.getPaymentId()
        finish()
    }

    override fun onPaymentError(i: Int, s: String?, paymentData: PaymentData?) {
        Constants.razorpaytransactionId = ""
        Constants.razorpayAmount = 0.0
        Toast.makeText(this, "Payment failed due to incomplete transaction", Toast.LENGTH_LONG).show()
        println("paymentData :- " + s.toString())
        println(paymentData.toString())
        finish()
    }
}


object Constants {

    var razorpayAmount = 0.0
    var razorpaytransactionId = ""
}