package com.example.app_modelo.data.model

import java.time.LocalDate

data class Pagos(
    val nroFactura: Int,
    val idSocio: Int,
    val importeTotal: Float,
    val fechaVencimiento: LocalDate,
    val estado: Boolean
)
