package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class EntregaCarnet01 : AppCompatActivity() {

    lateinit var etDocumento: TextInputEditText
    private lateinit var socioRepository: SocioRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrega_carnet01)

        socioRepository = SocioRepository(this)

        etDocumento = findViewById<TextInputEditText>(R.id.txtDocEntregaCarnet)

        val documento = etDocumento.text.toString().trim()

        val btnVolver = findViewById<Button>(R.id.btnVolver_Entrega)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
        }

        val btnEmitir = findViewById<Button>(R.id.btnEmitir)
        btnEmitir.setOnClickListener {
            val documento = etDocumento.text.toString().trim()
            if (documento.isNotEmpty()) {
                if (socioRepository.socioExiste(documento.toInt())) {
                    val intent = Intent(this, activity_imprimir_carnet::class.java)
                    intent.putExtra("DOCUMENTO", documento)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Socio no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese un documento", Toast.LENGTH_SHORT).show()
            }
        }
    }


}