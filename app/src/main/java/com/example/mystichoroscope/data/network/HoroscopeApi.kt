package com.example.mystichoroscope.data.network

import com.example.mystichoroscope.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApi {

    @POST(".")
    suspend fun getHoroscope(
        @Query("sing") sing:String,
        @Query("day") day:String
    ):Response<HoroscopeResponse>

    // Forzar crash para mostrar el interceptor

}