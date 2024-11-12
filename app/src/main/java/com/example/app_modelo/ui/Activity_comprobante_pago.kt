package com.example.app_modelo.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_modelo.R
import com.example.app_modelo.data.repository.SocioRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class activity_comprobante_pago : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comprobante_pago)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        socioRepository = SocioRepository(this)
        val documento = intent.getStringExtra("DOCUMENTO")

        if (documento != null) {
            val socio = socioRepository.obtenerSocioPorDocumento(documento.toInt())

            if (socio != null) {
                val idSocio = socio.idSocio
                val nombre = socio.nombreSocio
                val apellido = socio.apellidoSocio
                val cantidadActividades = socio.cantidadActividades
                val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

                val lblFecha = findViewById<TextView>(R.id.lbl_FechaComprobante)
                val lblNombre = findViewById<TextView>(R.id.lbl_NombreComprobante)
                val lblNroSocio = findViewById<TextView>(R.id.lbl_NroSocioComprobante)
                val lblActividades = findViewById<TextView>(R.id.lbl_ActividadesComprobante)

                lblFecha.text = "Fecha: $currentDate"
                lblNombre.text = "Nombre: $nombre $apellido"
                lblNroSocio.text = "Nro de Socio: $idSocio"
                lblActividades.text = "Actividades Contratadas: $cantidadActividades"
            }
        }
    }
}