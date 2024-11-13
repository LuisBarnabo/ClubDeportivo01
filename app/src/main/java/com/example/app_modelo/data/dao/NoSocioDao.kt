package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.app_modelo.data.database.NoSocioTable
import com.example.app_modelo.data.model.NoSocio

class NoSocioDao(private val database: SQLiteDatabase) {

    //Metodo para insertar un nuevo socio
    fun insertarNuevoNoSocio(noSocio: NoSocio): Long {
        val valuesNoSocio = ContentValues().apply {
            put("nombreNS", noSocio.nombreNS)
            put("apellidoNS", noSocio.apellidoNS)
            put("documentoNS", noSocio.documentoNS)
        }
        return database.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio)
    }

    fun obtenerNoSocios(): List<NoSocio> {
        val noSocioList = mutableListOf<NoSocio>()
        val cursor: Cursor = database.query(NoSocioTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val nombreNoSocio = cursor.getString(cursor.getColumnIndexOrThrow("nombreNS"))
            val apellidoNoSocio = cursor.getString(cursor.getColumnIndexOrThrow("apellidoNS"))
            val documentoNoSocio = cursor.getInt(cursor.getColumnIndexOrThrow("documentoNS"))
            noSocioList.add(NoSocio(nombreNoSocio,apellidoNoSocio,documentoNoSocio))
        }
        cursor.close()
        return noSocioList
    }
}