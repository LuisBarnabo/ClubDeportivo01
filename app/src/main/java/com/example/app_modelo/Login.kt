package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R.id.btnInicio
import com.example.app_modelo.R.id.btn_OlvidasteContra

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIniciarSesion = findViewById<Button>(btnInicio)
        btnIniciarSesion.setOnClickListener {
            val intentarInicio = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarInicio)
            finish()
        }

        val btnRecuperarContra = findViewById<TextView>(btn_OlvidasteContra)
        btnRecuperarContra.setOnClickListener {
            val intentarRecuperarContra = Intent(this, RecuperarContra_01::class.java)
            startActivity(intentarRecuperarContra)
        }

    }
}