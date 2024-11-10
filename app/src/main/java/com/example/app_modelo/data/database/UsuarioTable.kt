package com.example.app_modelo.data.database

object UsuarioTable {
    const val TABLE_NAME = "Usuario"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
            usuarioNombre TEXT,
            usuarioPass TEXT,
            idPuesto INTEGER,
            activo BOOLEAN DEFAULT 1,
            FOREIGN KEY (IDPuesto) REFERENCES Personal(IDPuesto)
        )
    """.trimIndent()

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}