package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btnBuscarSocioInscripcion
import com.example.app_modelo.R.id.btnVolverInscripcionAct
import com.example.app_modelo.data.repository.ActividadesRepository
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class InscripcionSocioActividad01 : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository
    private lateinit var actividadesRepository: ActividadesRepository
    private lateinit var lblDocumento: TextInputEditText
    private lateinit var actvActividades: AutoCompleteTextView
    private lateinit var btnInscripcionSocioAct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion_socio_actividad01)

        socioRepository = SocioRepository(this)
        actividadesRepository = ActividadesRepository(this)

        configuracionAutoCompleteTextView()


        val btnBuscarSocio = findViewById<Button>(btnBuscarSocioInscripcion)
        btnInscripcionSocioAct = findViewById(R.id.btnInscripcionSocioAct)

        //este metodo busca el socio en la base de datos con el dni ingresado
        btnBuscarSocio.setOnClickListener{
            val documento = lblDocumento.text.toString().trim()
            if (documento.isNotEmpty()) {
                val dni = documento.toInt()
                val socioEncontrado = socioRepository.obtenerSocioPorDocumento(dni)

                if (socioEncontrado != null) {
                    val nombreSocio = socioEncontrado.nombreSocio
                    Toast.makeText(this, "Nombre del socio: $nombreSocio", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Socio no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingresa un DNI", Toast.LENGTH_SHORT).show()
            }
        }

        btnInscripcionSocioAct.setOnClickListener{

        }
        val btnVolver = findViewById<Button>(btnVolverInscripcionAct)
        btnVolver.setOnClickListener {
            val intentarInscripcion = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarInscripcion)
            finish()
        }
    }

    private fun obtenerNombresActividades(): List<String> {
        val listaDeActividades = actividadesRepository.obtenerTodasLasActividades()
        return listaDeActividades.map { it.nombreActividad }
    }

    //este metodo llena la lista desplegable con todas las actividades
    private fun configuracionAutoCompleteTextView(){
        actvActividades = findViewById(R.id.actvActividades)

        val nombresActividades = obtenerNombresActividades()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            nombresActividades
        )
        actvActividades.setAdapter(adapter)
    }

}