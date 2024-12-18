package com.example.paymentgateway.phone_pay

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


object RetrofitClient {
    private const val BASE_URL = "https://api.phonepe.com/apis/hermes/"

    val apiService: PhonePeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhonePeApiService::class.java)
    }
}


interface PhonePeApiService {

    @Headers("Content-Type: application/json")
    @POST("pg/v1/pay")
    fun initiatePayment(
        @Body requestBody: Map<String, Any>
    ): Call<PaymentResponse>
}


data class PaymentResponse(
    val success: Boolean,
    val code: String,
    val message: String
)

