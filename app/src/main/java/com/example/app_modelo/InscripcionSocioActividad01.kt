package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R.id.btnVolver
import com.example.app_modelo.R.id.btnVolverInscripcionAct

class InscripcionSocioActividad01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion_socio_actividad01)

        val btnVolver = findViewById<Button>(btnVolverInscripcionAct)
        btnVolver.setOnClickListener {
            val intentarInscripcion = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarInscripcion)
        }
    }
}