package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.app_modelo.data.database.AppDatabaseHelper
import com.example.app_modelo.data.database.PersonalTable
import com.example.app_modelo.data.model.Personal

class PersonalDao(context: Context) {
    private val dbHelper = AppDatabaseHelper(context)
    private val database = dbHelper.writableDatabase

    //Metodo para insertar un registro
    fun insertarPersonal(personal: Personal): Long {
        val values = ContentValues().apply {
            put("idPuesto", personal.idPuesto)
            put("nombrePuesto", personal.nombrePuesto)
        }
        return database.insert(PersonalTable.TABLE_NAME, null, values)
    }

    //Metodo para obtener todos los registros
    fun obtenerTodosPersonal(): List<Personal> {
        val personalList = mutableListOf<Personal>()
        val cursor: Cursor =
            database.query(PersonalTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idPuesto = cursor.getInt(cursor.getColumnIndexOrThrow("idPuesto"))
            val nombrePuesto = cursor.getString(cursor.getColumnIndexOrThrow("nombrePuesto"))
            personalList.add(Personal(idPuesto, nombrePuesto))
        }
        cursor.close()
        return personalList
    }
}