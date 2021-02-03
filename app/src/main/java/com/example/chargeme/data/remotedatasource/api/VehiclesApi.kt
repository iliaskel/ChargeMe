package com.example.chargeme.data.remotedatasource.api

import com.example.chargeme.BuildConfig
import com.example.chargeme.data.remotedatasource.response.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import javax.crypto.Cipher.SECRET_KEY

interface VehiclesApi {
    @GET("/b/5fa8ff8dbd01877eecdb898f") @Headers("Content-Type: application/json")
    suspend fun getData(@Header("secret-key") key: String = BuildConfig.SECRET_KEY): Response
}