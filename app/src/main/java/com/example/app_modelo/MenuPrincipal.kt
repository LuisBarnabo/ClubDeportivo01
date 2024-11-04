package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R.id.btnCobrarCuota
import com.example.app_modelo.R.id.btnEntregarCarnet
import com.example.app_modelo.R.id.btnInscripcionAct
import com.example.app_modelo.R.id.btnRegistrarSocio

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val btnRegistarSocio = findViewById<Button>(btnRegistrarSocio)
        btnRegistarSocio.setOnClickListener{
            val intentarRegistro = Intent(this, Registrar_Socio_01::class.java)
            startActivity(intentarRegistro)
        }

        val btnEntregaDeCarnet = findViewById<Button>(btnEntregarCarnet)
        btnEntregaDeCarnet.setOnClickListener {
            val intentarEntregaCarnet = Intent(this, Entrega_Carnet_01::class.java)
            startActivity(intentarEntregaCarnet)
        }

        val btnInscrpcionActividad = findViewById<Button>(btnInscripcionAct)
        btnInscrpcionActividad.setOnClickListener {
            val intentarInscribirAct = Intent(this, Inscripcion_Socio_Actividad01::class.java)
            startActivity(intentarInscribirAct)
        }

//        val btnVencimientos = findViewById<Button>(btnVencimientos)
//        btnVencimientos.setOnClickListener {
//            val intentarVencimientos = Intent(this, )
//        }

        val btnCobrarCuota = findViewById<Button>(btnCobrarCuota)
        btnCobrarCuota.setOnClickListener {
            val intentarCuota = Intent(this, CobrarCuota::class.java)
            startActivity(intentarCuota)
        }
    }
}