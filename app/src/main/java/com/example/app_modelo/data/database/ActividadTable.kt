package com.example.app_modelo.data.database

object ActividadTable {
    const val TABLE_NAME = "Actividades"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            idActividad INTEGER PRIMARY KEY AUTOINCREMENT,
            nombreActividad TEXT,
            precioDiario REAL,
            cupoDisponible INTEGER
        )
    """.trimIndent()

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}