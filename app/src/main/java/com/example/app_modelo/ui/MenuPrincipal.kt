package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btnCobrarCuota
import com.example.app_modelo.R.id.btnEntregarCarnet
import com.example.app_modelo.R.id.btnInscripcionAct
import com.example.app_modelo.R.id.btnRegistrarSocio
import com.example.app_modelo.R.id.btnVencimientos

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val btnRegistarSocio = findViewById<Button>(btnRegistrarSocio)
        btnRegistarSocio.setOnClickListener {
            val intentarRegistro = Intent(this, RegistrarSocio01::class.java)
            startActivity(intentarRegistro)
        }

        val btnEntregaDeCarnet = findViewById<Button>(btnEntregarCarnet)
        btnEntregaDeCarnet.setOnClickListener {
            val intentarEntregaCarnet = Intent(this, EntregaCarnet01::class.java)
            startActivity(intentarEntregaCarnet)
        }

        val btnInscrpcionActividad = findViewById<Button>(btnInscripcionAct)
        btnInscrpcionActividad.setOnClickListener {
            val intentarInscribirAct = Intent(this, InscripcionSocioActividad01::class.java)
            startActivity(intentarInscribirAct)
        }

        val btnVencimientos = findViewById<Button>(btnVencimientos)
        btnVencimientos.setOnClickListener {
            val intentarVencimientos = Intent(this, Vencimientos01::class.java)
            startActivity(intentarVencimientos)
        }

        val btnCobrarCuota = findViewById<Button>(btnCobrarCuota)
        btnCobrarCuota.setOnClickListener {
            val intentarCuota = Intent(this, CobrarCuota::class.java)
            startActivity(intentarCuota)
        }

        val btnInscripcionSocioActividad01 = findViewById<Button>(btnInscripcionAct)
        btnInscripcionSocioActividad01.setOnClickListener {
            val intentarInscripcionAct = Intent(this, InscripcionSocioActividad01::class.java)
            startActivity(intentarInscripcionAct)
        }
    }
}