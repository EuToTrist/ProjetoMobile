package com.example.clinicease

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicease.R


class HomeActivity : AppCompatActivity() {

    private lateinit var botaoWeb: ImageView
    private lateinit var botaoWeb1: ImageView
    private lateinit var botaoWeb2: ImageView
    private lateinit var botaoWeb3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        botaoWeb = findViewById(R.id.Noticias)
        botaoWeb1 = findViewById(R.id.UPAS)
        botaoWeb2 = findViewById(R.id.Doença)
        botaoWeb3 = findViewById(R.id.Fav)

        botaoWeb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cnnbrasil.com.br/saude/")))
        }

        botaoWeb1.setOnClickListener {
            Telamapa()
        }

        botaoWeb2.setOnClickListener {

        }
        botaoWeb3.setOnClickListener {

        }

    }
    private fun Telamapa(){

        var segundaTela = Intent(this,MapsActivity::class.java)
        startActivity(segundaTela)
    }



}
