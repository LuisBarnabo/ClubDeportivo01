package com.example.app_modelo.ui

import PagosAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.model.Pagos
import com.example.app_modelo.data.repository.PagosRepository
import com.example.app_modelo.ui.MenuPrincipal
import java.text.SimpleDateFormat
import java.util.*

class Vencimientos01 : AppCompatActivity() {

    private lateinit var pagosRepository: PagosRepository
    private lateinit var lvVencimientos: ListView
    private lateinit var txtNoVencimientos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos01)

        pagosRepository = PagosRepository(this)
        lvVencimientos = findViewById(R.id.lvVencimientos)
        txtNoVencimientos = findViewById(R.id.txtNoVencimientos)

        val pagosVencidosHoy = obtenerPagosVencidosHoy()
        if (pagosVencidosHoy.isEmpty()) {
            txtNoVencimientos.visibility = View.VISIBLE
            lvVencimientos.visibility = View.GONE
        } else {
            txtNoVencimientos.visibility = View.GONE
            lvVencimientos.visibility = View.VISIBLE
            val adapter = PagosAdapter(this, pagosVencidosHoy)
            lvVencimientos.adapter = adapter
        }

        val btnVolver = findViewById<Button>(R.id.btn_Volver_Vencimientos)
        btnVolver.setOnClickListener {
            val intentVolver = Intent(this, MenuPrincipal::class.java)
            startActivity(intentVolver)
            finish()
        }
    }

    private fun obtenerPagosVencidosHoy(): List<Pagos> {
        val hoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return pagosRepository.obtenerPagosVencidosHoy().filter { it.fechaVencimiento == hoy }
    }
}