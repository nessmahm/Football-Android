package com.example.project.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    const val apiKey:String = "d67f02e3ed39f68f5df3de5bcba510a51eda15f9eb3c69f2c46f6f6345851a1e";

    private const val baseUrl ="https://apiv3.apifootball.com/APIkey="+apiKey
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    internal val retrofitService : FootballAPI by lazy { retrofit.create(FootballAPI::class.java) }

}
