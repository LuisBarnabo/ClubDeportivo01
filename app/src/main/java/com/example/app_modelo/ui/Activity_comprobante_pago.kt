package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_modelo.R
import com.example.app_modelo.data.repository.ActividadesRepository
import com.example.app_modelo.data.repository.NoSocioRepository
import com.example.app_modelo.data.repository.PreciosRepository
import com.example.app_modelo.data.repository.SocioRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class activity_comprobante_pago : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository
    private lateinit var noSocioRepository: NoSocioRepository
    private lateinit var preciosRepository: PreciosRepository
    private lateinit var actividadesRepository: ActividadesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comprobante_pago)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val documento = intent.getStringExtra("DOCUMENTO")
        val actividad: String? = intent.getStringExtra("ACTIVIDAD")

        if (documento != null) {
            crearComprobante(documento, actividad)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_Cancelar)
        btnCancelar.setOnClickListener{
            val intentCancelar = Intent(this, MenuPrincipal::class.java)
            startActivity(intentCancelar)
            finish()
        }

    }

    private fun crearComprobante(documento: String, actividad:String?) {
        socioRepository = SocioRepository(this)
        noSocioRepository = NoSocioRepository(this)
        preciosRepository = PreciosRepository(this)
        val socio = socioRepository.obtenerSocioPorDocumento(documento.toInt())
        val noSocio = noSocioRepository.obtenerNoSocioPorDocumento(documento.toInt())

        if (socio != null ) {
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

            val precio = preciosRepository.obtenerPrecioPorCantActividades(cantidadActividades)
            if (precio != null) {
                val lbl_ImporteComprobante = findViewById<TextView>(R.id.lbl_ImporteComprobante)
                lbl_ImporteComprobante.text = "Importe Abonado: $${precio.precio}"
            }
        } else if (noSocio != null) {
            actividadesRepository = ActividadesRepository(this)
            val actividadRealizada = actividad

            if (actividadRealizada != null) {
                val nombre = noSocio.nombreNS
                val apellido = noSocio.apellidoNS
                val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

                val lblFecha = findViewById<TextView>(R.id.lbl_FechaComprobante)
                val lblNombre = findViewById<TextView>(R.id.lbl_NombreComprobante)
                val lblNroSocio = findViewById<TextView>(R.id.lbl_NroSocioComprobante)
                val lblActividades = findViewById<TextView>(R.id.lbl_ActividadesComprobante)

                lblFecha.text = "Fecha: $currentDate"
                lblNombre.text = "Nombre: $nombre $apellido"
                lblNroSocio.text = "Nro de Documento: $documento"
                lblActividades.text = "Actividad Realizada: $actividadRealizada"

                val precio = actividadesRepository.obtenerActividad(actividadRealizada)
                if (precio != null) {
                    val lbl_ImporteComprobante = findViewById<TextView>(R.id.lbl_ImporteComprobante)
                    lbl_ImporteComprobante.text = "Importe Abonado: $${precio.precioDiario}"
            }
            }
        } else {
            Toast.makeText(this, "No se encontró el socio con el documento ingresado", Toast.LENGTH_SHORT).show()
        }
    }
}