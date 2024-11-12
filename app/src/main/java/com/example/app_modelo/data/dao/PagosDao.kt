package com.example.app_modelo.data.dao

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import com.example.app_modelo.data.database.PagosTable
import com.example.app_modelo.data.model.Pagos
import java.util.Locale

class PagosDao(private val database: SQLiteDatabase) {

    // Metodo para obtener los pagos vencidos hoy
    fun obtenerPagosVencidosHoy(): List<Pagos> {
        val pagosList = mutableListOf<Pagos>()
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(java.util.Date())

        val cursor: Cursor = database.query(
            PagosTable.TABLE_NAME,  // Nombre de la tabla
            null,  // Seleccionamos todas las columnas
            "fechaVencimiento = ?",  // Filtro para la fecha de vencimiento
            arrayOf(today),  // Parámetros de la consulta
            null,  // No agrupamos resultados
            null,  // No ordenamos
            null   // No limitamos
        )

        while (cursor.moveToNext()) {
            val nroFactura = cursor.getInt(cursor.getColumnIndexOrThrow("nroFactura"))
            val idSocio = cursor.getInt(cursor.getColumnIndexOrThrow("idSocio"))
            val importeTotal = cursor.getFloat(cursor.getColumnIndexOrThrow("importeTotal"))
            val fechaVencimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaVencimiento"))
            val estado = cursor.getInt(cursor.getColumnIndexOrThrow("estado")) != 0

            val pago = Pagos(nroFactura, idSocio, importeTotal, fechaVencimiento, estado)
            pagosList.add(pago)
        }

        cursor.close()
        return pagosList
    }

}