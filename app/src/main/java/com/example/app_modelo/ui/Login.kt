package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btnInicio
import com.example.app_modelo.R.id.btn_OlvidasteContra
import com.example.app_modelo.data.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    private lateinit var etUsuario: TextInputEditText
    private lateinit var etContrasena: TextInputEditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioRepository = UsuarioRepository(this)

        etUsuario = findViewById(R.id.txtUsuario)
        etContrasena = findViewById(R.id.txtContrasena)
        btnIniciarSesion = findViewById(btnInicio)
        btnIniciarSesion.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                if (usuarioRepository.verificarCredenciales(usuario, contrasena)) {
                    Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MenuPrincipal::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        val btnRecuperarContra = findViewById<TextView>(btn_OlvidasteContra)
        btnRecuperarContra.setOnClickListener {
            val intentarRecuperarContra = Intent(this, RecuperarContra01::class.java)
            startActivity(intentarRecuperarContra)
        }

    }
}