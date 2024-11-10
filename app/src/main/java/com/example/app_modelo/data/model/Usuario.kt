package com.example.app_modelo.data.model

data class Usuario(
    val idUsuario: Int,
    val usuarioNombre: String,
    val usuarioPass: String,
    val idPuesto: Int,
    val activo: Boolean
)
