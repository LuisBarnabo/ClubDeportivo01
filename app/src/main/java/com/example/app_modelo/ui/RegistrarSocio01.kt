package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.data.model.NoSocio
import com.example.app_modelo.data.repository.NoSocioRepository
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class RegistrarSocio01 : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository
    private lateinit var noSocioRepository: NoSocioRepository
    private lateinit var tietNombreSocio: TextInputEditText
    private lateinit var tietApellidoSocio: TextInputEditText
    private lateinit var tietDocumentoSocio: TextInputEditText
    private lateinit var actvCondicion: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_socio01)

        socioRepository = SocioRepository(this)
        noSocioRepository = NoSocioRepository((this))
        tietNombreSocio = findViewById(R.id.lblNombre)
        tietApellidoSocio = findViewById(R.id.lblApellido)
        tietDocumentoSocio = findViewById(R.id.lblDocumento)
        actvCondicion = findViewById(R.id.condicionesAutoCompleteTextView)


        val btnRegistrarSocio = findViewById<Button>(R.id.btnAceptarRegistro)
        btnRegistrarSocio.setOnClickListener{
            val nombre = tietNombreSocio.text.toString().trim()
            val apellido = tietApellidoSocio.text.toString().trim()
            val doc = tietDocumentoSocio.text.toString().trim()
            val condicion = actvCondicion.text.toString().trim()

            if (nombre.isNotEmpty() && apellido.isNotEmpty() && doc.isNotEmpty() && condicion.isNotEmpty()) {
                //parseamos el string obtenido de documento a int
                val documento: Int = try {
                    doc.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Documento inválido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val resultado: Long
                if (condicion == "Socio"){
                    resultado = socioRepository.registrarNuevoSocio(nombre,apellido,documento)
                } else if (condicion == "No Socio"){
                    // llamar a la clase NOSOCIO
                    resultado = noSocioRepository.registrarNuevoNoSocio(nombre,apellido, documento)

                } else {
                    Toast.makeText(this, "Condición inválida", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                // Verificar el resultado de la operación
                if (resultado > 0) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)
        btnCancelar.setOnClickListener {
            val intentarCancelar = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarCancelar)
        }
    }
}