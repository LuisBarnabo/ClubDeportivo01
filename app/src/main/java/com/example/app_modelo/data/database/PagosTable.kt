package com.example.app_modelo.data.database

object PagosTable {
    const val TABLE_NAME = "Pagos"
    val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            nroFactura INTEGER PRIMARY KEY AUTOINCREMENT,
            idSocio INTEGER,
            importeTotal REAL,
            fechaVencimiento TEXT,
            estado BOOLEAN,
            FOREIGN KEY (idSocio) REFERENCES Socio(idSocio)
        )
    """.trimIndent()
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}