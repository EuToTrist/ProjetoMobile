package com.avanade.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.GeneratedAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.currencyconverter.api.Endpoint
import com.avanade.currencyconverter.util.NetworkUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view) // Corrigido o nome da variável
        recyclerView.layoutManager = LinearLayoutManager(this)

        getCurrencies()
    }

    private fun getCurrencies() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://clinicease-d5bq34i2.b4a.run/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getCurrencies().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val dataList = mutableListOf<String>()
                    val sintomasObject = response.body()

                    sintomasObject?.keySet()?.forEach { doenca ->
                        dataList.add(doenca)
                    }

                    adapter = CardAdapter(dataList)
                    recyclerView.adapter = adapter
                } else {
                    println("erro")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("erro na requisiçao")
            }
        })
    }
}
