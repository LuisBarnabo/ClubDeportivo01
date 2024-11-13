package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.repository.SocioRepository

class activity_imprimir_carnet : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imprimir_carnet)

        socioRepository = SocioRepository(this)
        val documento = intent.getStringExtra("DOCUMENTO")

        if (documento != null) {
            val socio = socioRepository.obtenerSocioPorDocumento(documento.toInt())

            if (socio != null) {
                val idSocio = socio.idSocio
                val nombre = socio.nombreSocio
                val apellido = socio.apellidoSocio

                val lblNroSocioCarnet = findViewById<TextView>(R.id.lbl_NroSocioCarnet)
                val lblNombreCarnet = findViewById<TextView>(R.id.lbl_NombreCarnet)

                lblNroSocioCarnet.text = "Número de socio: $idSocio"
                lblNombreCarnet.text = "Nombre: $nombre $apellido"
            }
        }

        val btnCancelar = findViewById<Button>(R.id.btn_Cancelar)
        btnCancelar.setOnClickListener {
            val intentCancelar = Intent(this, MenuPrincipal::class.java)
            startActivity(intentCancelar)
            finish()
        }
    }
}