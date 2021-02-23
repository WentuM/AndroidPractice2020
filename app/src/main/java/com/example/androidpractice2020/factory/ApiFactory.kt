package com.example.androidpractice2020.factory

import com.example.androidpractice2020.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private val QUERY_API_KEY = "appid"
    private val QUERY_UNIT = "units"


    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.API_KEY)
            .build().let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    private val unitsInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter(QUERY_UNIT, "metric")
            .build().let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(unitsInterceptor)
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_ENDPOINT)
            .build()
    }

    val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }


}