package com.example.app_modelo.data.database

object PersonalTable {
    const val TABLE_NAME = "Personal"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            idPuesto INTEGER PRIMARY KEY,
            nombrePuesto TEXT
        )
        """.trimIndent()

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}