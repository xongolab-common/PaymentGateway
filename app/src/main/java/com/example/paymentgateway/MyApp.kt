package com.example.paymentgateway

import android.app.Application
/*import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.createorder.CreateOrderActions
import com.paypal.checkout.createorder.Order
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.CurrencyCode
import com.paypal.checkout.order.PurchaseUnit*/

class MyApp2 : Application() {
    override fun onCreate() {
        super.onCreate()

      /*  PayPalCheckout.setConfig( // Unresolved reference: PayPalCheckout
            clientId = "YOUR_CLIENT_ID",
            environment = PayPalCheckout.Environment.SANDBOX
        )*/

    }
}

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configure PayPal SDK with your client ID and environment
     /*   PayPalCheckout.setConfig(
            clientId = "YOUR_CLIENT_ID",
            environment = PayPalEnvironment.SANDBOX // Use SANDBOX for testing
        )*/
    }
}
