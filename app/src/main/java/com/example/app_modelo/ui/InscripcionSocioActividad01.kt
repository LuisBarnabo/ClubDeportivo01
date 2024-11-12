package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btnVolverInscripcionAct
import com.example.app_modelo.data.repository.ActividadesRepository
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class InscripcionSocioActividad01 : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository
    private lateinit var actividadesRepository: ActividadesRepository
    private lateinit var lblDocumento: TextInputEditText
    private lateinit var actvActividades: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion_socio_actividad01)

        socioRepository = SocioRepository(this)
        actvActividades = findViewById(R.id.actvActividades)






        val btnVolver = findViewById<Button>(btnVolverInscripcionAct)
        btnVolver.setOnClickListener {
            val intentarInscripcion = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarInscripcion)
        }
    }

}