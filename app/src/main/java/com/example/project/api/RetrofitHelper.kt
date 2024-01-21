package com.example.project.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    const val apiKey:String = "d67f02e3ed39f68f5df3de5bcba510a51eda15f9eb3c69f2c46f6f6345851a1e";

    private const val baseUrl = "https://apiv3.apifootball.com"
    /**
     * The Retrofit object with Gson converter.
     */

    fun createRetrofit() : Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("APIkey", this.apiKey)
                    .build()

                val request = original.newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit
    }

    private val retrofit = createRetrofit()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    internal val retrofitService : FootballAPI by lazy { retrofit.create(FootballAPI::class.java) }

}
