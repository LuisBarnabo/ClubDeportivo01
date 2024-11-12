package com.example.app_modelo.data.model

data class Pagos(
    val nroFactura: Int,
    val idSocio: Int,
    val importeTotal: Float,
    val fechaVencimiento: String,
    val estado: Boolean
)
