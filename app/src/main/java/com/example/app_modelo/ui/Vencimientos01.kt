package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.dao.PagosDao
import com.example.app_modelo.data.repository.PagosRepository

class Vencimientos01 : AppCompatActivity() {

    private lateinit var pagosRepository: PagosRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos01)

        pagosRepository = PagosRepository(this)

        val btnEmitir = findViewById<Button>(R.id.btn_EmitirVencimientos)
        btnEmitir.setOnClickListener {
            pagosRepository.obtenerPagosVencidosHoy()
        }

        val btnVolver = findViewById<Button>(R.id.btn_Volver_Vencimientos)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
        }
    }
}