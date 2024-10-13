package com.example.app_modelo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R.id.btnEntregarCarnet
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
    }
}