package com.example.clinicease.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    @GET("/doencas")
    fun getCurrencies(): Call<JsonObject>

    @GET("/{doenca}")
    fun getSintomas(@Path(value = "doenca", encoded = true) from: String): Call<JsonObject>
}