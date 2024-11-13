package com.example.app_modelo.data.dao

import android.content.ContentValues
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
}