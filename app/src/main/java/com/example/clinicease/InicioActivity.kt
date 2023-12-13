package com.example.clinicease

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {

    private lateinit var botaoWeb: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoWeb = findViewById(R.id.Noticias)

        botaoWeb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("")))
        }
    }
}
