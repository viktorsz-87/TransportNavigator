package com.andronest.retrofit

import com.andronest.constants.Constants.Retrofit.BASE_URL_NEARBY_STOPS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val retrofitApi: RetrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_NEARBY_STOPS)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitApi::class.java)
    }
}