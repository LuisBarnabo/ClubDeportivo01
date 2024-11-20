package com.example.app_modelo.data.repository

import android.content.Context
import com.example.app_modelo.data.dao.PagosDao
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.model.Pagos


class PagosRepository(context: Context) {
    private val pagosDao: PagosDao
    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        pagosDao = PagosDao(database)
    }
    fun obtenerPagosVencidosHoy(): List<Pagos> {
        return pagosDao.obtenerPagosVencidosHoy()
    }
}