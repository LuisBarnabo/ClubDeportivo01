package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.app_modelo.data.database.SocioTable
import com.example.app_modelo.data.model.Socio

class SocioDao (private val database: SQLiteDatabase) {



    //Metodo para insertar un nuevo socio
    fun insertarNuevoSocio(socio: Socio): Long {
        val valuesSocio = ContentValues().apply {
            put("nombreSocio", socio.nombreSocio)
            put("apellidoSocio", socio.apellidoSocio)
            put("documentoSocio", socio.documentoSocio)
            put("cantidadActividades", socio.cantidadActividades)
        }
        return database.insert(SocioTable.TABLE_NAME, null, valuesSocio)
    }

    //Metodo para obtener todos los socios
    fun obtenerSocios(): List<Socio> {
        val socioList = mutableListOf<Socio>()
        val cursor: Cursor = database.query(SocioTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idSocio = cursor.getInt(cursor.getColumnIndexOrThrow("idSocio"))
            val nombreSocio = cursor.getString(cursor.getColumnIndexOrThrow("nombreSocio"))
            val apellidoSocio = cursor.getString(cursor.getColumnIndexOrThrow("apellidoSocio"))
            val documentoSocio = cursor.getInt(cursor.getColumnIndexOrThrow("documentoSocio"))
            val cantidadActividades = cursor.getInt(cursor.getColumnIndexOrThrow("cantidadActividades"))
            socioList.add(Socio(idSocio,nombreSocio,apellidoSocio,documentoSocio,cantidadActividades))
        }
        cursor.close()
        return socioList
    }

    //metodo para incrementar en 1 la cantidad de actividades
    fun incrementarActividadSocio(idSocio: Int): Int {
        val values = ContentValues().apply {
            put("cantidadActividades", "cantidadActividades + 1")
        }
        return database.update(
            SocioTable.TABLE_NAME,
            values,
            "idSocio = ?",
            arrayOf(idSocio.toString())
        )
    }
}