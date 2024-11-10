package com.example.app_modelo.data.database

object SocioTable {
    const val TABLE_NAME = "Socio"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            idSocio INTEGER PRIMARY KEY AUTOINCREMENT,
            nombreSocio TEXT,
            apellidoSocio TEXT,
            documentoSocio INTEGER,
            cantidadActividades INTEGER
        )
    """.trimIndent()
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}