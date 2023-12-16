package com.avanade.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avanade.currencyconverter.api.Endpoint
import com.avanade.currencyconverter.util.NetworkUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun getSintomasDeDoencas(doenca: String) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://clinicease-d5bq34i2.b4a.run/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getSintomas(doenca).enqueue(object  : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val sintomasObejct = response.body()

                    sintomasObejct?.getAsJsonArray("sintomas")?.forEach {
                        println(it.asString)
                    }
                }else {
                    println("deu erro")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Erro na requisição: ${t.message}")
            }
        })
    }

}