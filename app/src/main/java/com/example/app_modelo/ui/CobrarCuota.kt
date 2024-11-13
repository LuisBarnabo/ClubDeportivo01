package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btn_EmitirCuota
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class CobrarCuota : AppCompatActivity() {

    private lateinit var etDocumento: TextInputEditText
    private lateinit var socioRepository: SocioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cobrar_cuota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        socioRepository = SocioRepository(this)
        etDocumento = findViewById<TextInputEditText>(R.id.txtDocCobrarCuota)

        val btnEmitirComprobante = findViewById<Button>(btn_EmitirCuota)
        btnEmitirComprobante.setOnClickListener() {
            val documento = etDocumento.text.toString().trim()
            if (documento.isNotEmpty()) {
                if (socioRepository.socioExiste(documento.toInt())) {
                    val intentComprobante = Intent(this, activity_comprobante_pago::class.java)
                    intentComprobante.putExtra("DOCUMENTO", documento)
                    startActivity(intentComprobante)
                } else {
                    Toast.makeText(this, "Documento no registrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese un documento", Toast.LENGTH_SHORT).show()
            }
        }

        val btnVolver = findViewById<Button>(R.id.btnVolver_Cobrar)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
            finish()
        }
    }
}