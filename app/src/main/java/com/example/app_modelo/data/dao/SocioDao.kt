package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
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

    //este metodo obtiene el socio por dni mas directo que llevar todos los datos de la tabla socio
    fun obtenerSocioPorDocumentoDirecto(documento: Int): Socio? {

        val cursor = database.rawQuery(
            "SELECT * FROM ${SocioTable.TABLE_NAME} WHERE documentoSocio = ?",
            arrayOf(documento.toString())
        )

        var socio: Socio? = null
        if (cursor.moveToFirst()) {
            val idSocio = cursor.getInt(cursor.getColumnIndexOrThrow("idSocio"))
            val nombreSocio = cursor.getString(cursor.getColumnIndexOrThrow("nombreSocio"))
            val apellidoSocio = cursor.getString(cursor.getColumnIndexOrThrow("apellidoSocio"))
            val documentoSocio = cursor.getInt(cursor.getColumnIndexOrThrow("documentoSocio"))
            val cantidadActividades = cursor.getInt(cursor.getColumnIndexOrThrow("cantidadActividades"))
            socio = Socio(idSocio, nombreSocio, apellidoSocio, documentoSocio, cantidadActividades)
        }
        cursor.close()
        return socio
    }

    //metodo para incrementar en 1 la cantidad de actividades
    fun incrementarActividadSocio(idSocio: Int): Boolean {
        return try {
            // Consulta SQL para incrementar la cantidad de actividades
            val query = """
            UPDATE ${SocioTable.TABLE_NAME} 
            SET cantidadActividades = cantidadActividades + 1 
            WHERE idSocio = ?
        """
            database.execSQL(query, arrayOf(idSocio.toString()))
            true
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error al incrementar actividad: ${e.message}")
            false
        }
    }
}