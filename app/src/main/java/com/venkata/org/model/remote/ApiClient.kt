package com.venkata.org.model.remote

import com.venkata.org.model.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL_API)
            client(okHttp)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }



    val retrofitImage: Retrofit by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL_IMAGES)
            client(okHttp)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}