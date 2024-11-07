package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R.id.btnRecuperaContra
import com.example.app_modelo.R.id.btnVolver

class RecuperarContra01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contra01)

        val btnRecuperarContra = findViewById<Button>(btnRecuperaContra)
        btnRecuperarContra.setOnClickListener{
            val intentarRecuperarContra = Intent(this,RecuperarContra02::class.java)
            startActivity(intentarRecuperarContra)
        }

        val btnVolver = findViewById<Button>(btnVolver)
        btnVolver.setOnClickListener {
            val intentarVolver = Intent(this, Login::class.java)
            startActivity(intentarVolver)
        }

    }
}