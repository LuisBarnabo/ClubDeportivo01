package com.example.app_modelo.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.app_modelo.data.database.UsuarioTable
import com.example.app_modelo.data.model.Usuario

class UsuarioDao(private val database: SQLiteDatabase) {


    //Metodo para insertar un registro
    fun insertarUsuario(usuario: Usuario): Long {
        val values = ContentValues().apply {
            put("usuarioNombre", usuario.usuarioNombre)
            put("usuarioPass", usuario.usuarioPass)
            put("idPuesto", usuario.idPuesto)
        }
        return database.insert(UsuarioTable.TABLE_NAME, null, values)
    }

    //Metodo para obtener todos los registros
    fun obtenerTodosLosUsuarios(): List<Usuario> {
        val usuarioList = mutableListOf<Usuario>()
        val cursor: Cursor =
            database.query(UsuarioTable.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario"))
            val usuarioNombre = cursor.getString(cursor.getColumnIndexOrThrow("usuarioNombre"))
            val usuarioPass = cursor.getString(cursor.getColumnIndexOrThrow("usuarioPass"))
            val idPuesto = cursor.getInt(cursor.getColumnIndexOrThrow("idPuesto"))
            val activo = cursor.getInt(cursor.getColumnIndexOrThrow("activo")) != 0
            usuarioList.add(Usuario(idUsuario, usuarioNombre, usuarioPass, idPuesto, activo))
        }
        cursor.close()
        return usuarioList
    }

    // Metodo para desactivar un registro
    fun desactivarUsuarioPorId(idUsuario: Int): Int {
        val values = ContentValues().apply {
            put("activo", 0)
        }
        return database.update(
            UsuarioTable.TABLE_NAME,
            values,
            "idUsuario = ?",
            arrayOf(idUsuario.toString())
        )
    }


}