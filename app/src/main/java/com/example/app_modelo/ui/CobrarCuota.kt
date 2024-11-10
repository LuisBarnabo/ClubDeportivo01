package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btn_EmitirCuota

class CobrarCuota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cobrar_cuota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEmitirComprobante = findViewById<Button>(btn_EmitirCuota)
        btnEmitirComprobante.setOnClickListener() {
            val intentarComprobante = Intent(this, activity_comprobante_pago::class.java)
            startActivity(intentarComprobante)
        }

        val btnVolver = findViewById<Button>(R.id.btnVolver_Cobrar)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
        }
    }
}