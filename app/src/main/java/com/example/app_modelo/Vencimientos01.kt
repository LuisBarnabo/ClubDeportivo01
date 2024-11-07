package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Vencimientos01 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos01)

        val btnVolver = findViewById<Button>(R.id.btn_Volver_Vencimientos)
        btnVolver.setOnClickListener{
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
        }

    }
}