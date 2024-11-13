package com.example.app_modelo.data.database

object PreciosTable {
    const val TABLE_NAME = "Precios"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            cantidadActividades INTEGER,
            precio REAL
        )
    """.trimIndent()

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}