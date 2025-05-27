package com.beans.cmstest.data.network

import com.beans.cmstest.data.repository.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://jellybellywikiapi.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}