package com.example.app_modelo.data.repository

import android.content.Context
import com.example.app_modelo.data.dao.NoSocioDao
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.model.NoSocio
import com.example.app_modelo.data.model.Socio

class NoSocioRepository(context: Context) {

    private val noSocioDao: NoSocioDao
    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        noSocioDao = NoSocioDao(database)
    }

    fun registrarNuevoNoSocio(nombre: String, apellido: String, documento: Int): Long {
        // Crear el objeto NoSocio
        val nuevoNoSocio = NoSocio(
            nombreNS = nombre,
            apellidoNS = apellido,
            documentoNS = documento,
        )

        // Llamar al metodo insertarNuevoSocio del Dao
        return noSocioDao.insertarNuevoNoSocio(nuevoNoSocio)
    }

    fun noSocioExiste(documento: Int): Boolean {
        val noSocios = noSocioDao.obtenerNoSocios()
        return noSocios.any { it.documentoNS == documento }
    }

    fun obtenerNoSocioPorDocumento(documento: Int): NoSocio? {
        val noSocios = noSocioDao.obtenerNoSocios()
        return noSocios.find { it.documentoNS == documento }
    }
}