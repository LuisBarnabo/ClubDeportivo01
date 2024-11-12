package com.example.app_modelo.data.repository

import android.content.Context
import com.example.app_modelo.data.dao.SocioDao
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.model.Socio

class SocioRepository(context: Context) {

    private val socioDao: SocioDao
    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        socioDao = SocioDao(database)
    }

    fun registrarNuevoSocio(nombre: String, apellido: String, documento: Int): Long {
        // Crear el objeto Socio
        val nuevoSocio = Socio(
            idSocio = 0,
            nombreSocio = nombre,
            apellidoSocio = apellido,
            documentoSocio = documento,
            cantidadActividades = 0 // Inicialmente 0, o como prefieras inicializarlo
        )

        // Llamar al metodo insertarNuevoSocio del Dao
        return socioDao.insertarNuevoSocio(nuevoSocio)
    }

    fun obtenerTodosLosSocios(): List<Socio> = socioDao.obtenerSocios()

    fun sumarActividad(idSocio: Int) {
        socioDao.incrementarActividadSocio(idSocio)
    }
}