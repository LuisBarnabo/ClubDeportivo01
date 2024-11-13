package com.example.app_modelo.data.repository

import android.content.Context
import com.example.app_modelo.data.model.Actividades
import com.example.app_modelo.data.dao.ActividadesDao
import com.example.app_modelo.data.database.AppDatabaseHelper

class ActividadesRepository (context: Context) {

    private val actividadesDao: ActividadesDao

    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        actividadesDao = ActividadesDao(database)
    }

    fun obtenerTodasLasActividades(): List<Actividades> = actividadesDao.obtenerTodasLasActividades()

    fun restarCupo(idActividad: Int): Boolean {
        if(actividadesDao.restarCupoActividad(idActividad)){
            return true
        }
        return false
    }

}