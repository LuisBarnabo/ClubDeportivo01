package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.app_modelo.data.database.ActividadesTable
import com.example.app_modelo.data.model.Actividades

class ActividadesDao(private val database: SQLiteDatabase) {

    //Metodo para insertar una nueva actividad
    fun insertarActividadNueva(actividades: Actividades): Long {
        val valuesAct = ContentValues().apply {
            put("nombreActiviad",actividades.nombreActividad)
            put("precioDiario", actividades.precioDiario)
            put("cupoDisponible", actividades.cupoDisponible)
        }
        return database.insert(ActividadesTable.TABLE_NAME, null, valuesAct)
    }

    //Metodo para obtener todas las actividades
    fun obtenerTodasLasActividades(): List<Actividades> {
        val actividadList = mutableListOf<Actividades>()
        val cursor: Cursor = database.query(ActividadesTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()){
            val idActividad = cursor.getInt(cursor.getColumnIndexOrThrow("idActividad"))
            val nombreActividad = cursor.getString(cursor.getColumnIndexOrThrow("nombreActividad"))
            val precioDiario = cursor.getFloat(cursor.getColumnIndexOrThrow("precioDiario"))
            val cupoDisponible = cursor.getInt(cursor.getColumnIndexOrThrow("cupoDisponible"))
            actividadList.add(Actividades(idActividad, nombreActividad, precioDiario, cupoDisponible))
        }
        cursor.close()
        return actividadList
    }

    //metodo para restar 1 al cupo disponible de la actividad elegida
    fun restarCupoActividad(idActividad: Int): Boolean {
        return try {
            // Ejecuta la operación para restar el cupo
            val query = """
            UPDATE ${ActividadesTable.TABLE_NAME} 
            SET cuposDisponibles = cuposDisponibles - 1 
            WHERE id = ? AND cuposDisponibles > 0
        """
            database.execSQL(query, arrayOf(idActividad.toString()))

            // Si no hay errores, devolvemos true indicando éxito
            true
        } catch (e: Exception) {
            // Manejo del error: muestra un mensaje de log y devuelve false
            Log.e("DatabaseError", "Error al restar cupo: ${e.message}")
            false
        }
    }
}