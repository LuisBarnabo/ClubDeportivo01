package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.dao.PagosDao
import com.example.app_modelo.data.repository.PagosRepository

class Vencimientos01 : AppCompatActivity() {

    private lateinit var pagosRepository: PagosRepository
    private lateinit var lvVencimientos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos01)

        pagosRepository = PagosRepository(this)
        lvVencimientos = findViewById(R.id.lvVencimientos)

        val btnEmitir = findViewById<Button>(R.id.btn_EmitirVencimientos)
        btnEmitir.setOnClickListener {
            listarPagosVencidosHoy()
        }

        val btnVolver = findViewById<Button>(R.id.btn_Volver_Vencimientos)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
        }
    }

    private fun listarPagosVencidosHoy() {
        val pagosVencidosHoy = pagosRepository.obtenerPagosVencidosHoy()
        val adapter = PagosAdapter(this, pagosVencidosHoy)
        lvVencimientos.adapter = adapter
    }
}