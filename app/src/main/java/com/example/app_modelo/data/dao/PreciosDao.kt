package com.example.app_modelo.data.dao

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.database.PreciosTable
import com.example.app_modelo.data.model.Precios

class PreciosDao(private val database: SQLiteDatabase) {

    // Metodo para obtener los precios por actividades
    fun obtenerPrecios(): List<Precios> {
        val preciosList = mutableListOf<Precios>()
        val cursor: Cursor =
            database.query(PreciosTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val cantidadActividades = cursor.getInt(cursor.getColumnIndexOrThrow("cantidadActividades"))
            val precio = cursor.getFloat(cursor.getColumnIndexOrThrow("precio"))
            preciosList.add(Precios(cantidadActividades, precio))
        }
        cursor.close()
        return preciosList
    }
}