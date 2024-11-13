package com.example.app_modelo.data.repository

import android.content.Context
import android.util.Log
import com.example.app_modelo.data.dao.PreciosDao
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.model.Precios


class PreciosRepository(context: Context) {

    private val preciosDao: PreciosDao
    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        preciosDao = PreciosDao(database)
    }

    fun obtenerPrecios(): List<Precios> = preciosDao.obtenerPrecios()

    fun obtenerPrecioPorCantActividades(cantidad: Int): Precios? {
        Log.d("PreciosRepository", "Entering obtenerPrecioPorCantActividades() with cantidadActividades: $cantidad")
        val precios = obtenerPrecios()
        Log.d("PreciosRepository", "Exiting obtenerPrecioPorCantActividades() with precios: $precios")
        return precios.find { it.cantidadActividades == cantidad }
    }
}