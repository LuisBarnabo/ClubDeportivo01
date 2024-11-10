package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R

class RegistrarSocio01 : AppCompatActivity() {

    /*private lateinit var binding: ActivityRegistrarSocio01Binding*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*binding = ActivityRegistrarSocio01Binding.inflate(layoutInflater)*/
        setContentView(R.layout.activity_registrar_socio01) //setContentView(binding.root)

        //LO QUE ESTA COMENTADO ES UN METODO ANTIGUO (binding inflate) QUE NO SE USA PERO
        //LO DEJO COMENTADO POR SI SE NECESITA EN UN FUTURO
        /*val condiciones = resources.getStringArray(R.array.condicionesSocio)
        val adapter = ArrayAdapter(this, R.layout.list_item_condicion, condiciones)

        with(binding.condicionesAutoCompleteTextView){
            setAdapter(adapter)
        }*/

        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)
        btnCancelar.setOnClickListener {
            val intentarCancelar = Intent(this, MenuPrincipal::class.java)
            startActivity(intentarCancelar)
        }
    }
}