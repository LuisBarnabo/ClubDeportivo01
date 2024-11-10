package com.example.app_modelo.data.database

object NoSocioTable {
    const val TABLE_NAME = "NoSocio"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            nombreNS TEXT,
            apellidoNS TEXT,
            documentoNS INTEGER PRIMARY KEY
        )
    """.trimIndent()

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}