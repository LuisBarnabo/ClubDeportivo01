package com.example.app_modelo.data.repository

import android.content.Context
import com.example.app_modelo.data.dao.UsuarioDao
import com.example.app_modelo.data.database.AppDatabaseHelper

class UsuarioRepository(context: Context) {

    private val usuarioDao: UsuarioDao

    init {
        val dbHelper = AppDatabaseHelper(context)
        val database = dbHelper.writableDatabase
        usuarioDao = UsuarioDao(database)
    }

    //Verifica si el usuario y clave estan en la ddbb
    fun verificarCredenciales(usuario: String, clave: String): Boolean {
        val usuarios = usuarioDao.obtenerTodosLosUsuarios()
        return usuarios.any {
            it.usuarioNombre == usuario && it.usuarioPass == clave && it.activo
        }
    }

    // Metodo para desactivar un usuario en vez de eliminarlo
    fun desactivarUsuario(idUsuario: Int): Boolean {
        val filasAfectadas = usuarioDao.desactivarUsuarioPorId(idUsuario)
        return filasAfectadas > 0
    }
}