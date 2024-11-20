package com.example.app_modelo.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "CLUBDEPORTIVO.db"
        private val DATABASE_VERSION = 5
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.execSQL("PRAGMA foreign_keys = ON;")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(PersonalTable.CREATE_TABLE)
        db.execSQL(UsuarioTable.CREATE_TABLE)
        db.execSQL(SocioTable.CREATE_TABLE)
        db.execSQL(NoSocioTable.CREATE_TABLE)
        db.execSQL(PagosTable.CREATE_TABLE)
        db.execSQL(ActividadesTable.CREATE_TABLE)
        db.execSQL(PreciosTable.CREATE_TABLE)

        // Precarga de datos
        insertarDatosIniciales(db)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(PersonalTable.DROP_TABLE)
        db.execSQL(UsuarioTable.DROP_TABLE)
        db.execSQL(SocioTable.DROP_TABLE)
        db.execSQL(NoSocioTable.DROP_TABLE)
        db.execSQL(PagosTable.DROP_TABLE)
        db.execSQL(ActividadesTable.DROP_TABLE)
        db.execSQL(PreciosTable.DROP_TABLE)
        onCreate(db)
    }

    private fun insertarDatosIniciales(db: SQLiteDatabase) {

        //Insertar datos iniciales para la tabla Personal
        val valuesPersonal1 = ContentValues()
        valuesPersonal1.put("idPuesto", 120)
        valuesPersonal1.put("nombrePuesto", "Administrador")
        db.insert(PersonalTable.TABLE_NAME, null, valuesPersonal1)

        val valuesPersonal2 = ContentValues()
        valuesPersonal2.put("idPuesto", 121)
        valuesPersonal2.put("nombrePuesto", "Empleado")
        db.insert(PersonalTable.TABLE_NAME, null, valuesPersonal2)

        // Insertar datos iniciales para la tabla Usuario
        val valuesUsuario1 = ContentValues()
        valuesUsuario1.put("idUsuario", 100)
        valuesUsuario1.put("usuarioNombre", "Macarena")
        valuesUsuario1.put("usuarioPass", "123456")
        valuesUsuario1.put("idPuesto", 120)
        db.insert(UsuarioTable.TABLE_NAME, null, valuesUsuario1)

        val valuesUsuario2 = ContentValues()
        valuesUsuario2.put("idUsuario", 101)
        valuesUsuario2.put("usuarioNombre", "admin")
        valuesUsuario2.put("usuarioPass", "1234")
        valuesUsuario2.put("idPuesto", 120)
        db.insert(UsuarioTable.TABLE_NAME, null, valuesUsuario2)

        // Insertar datos iniciales para la tabla Socio
        val valuesSocio1 = ContentValues()
        valuesSocio1.put("idSocio", 100)
        valuesSocio1.put("nombreSocio", "Ana")
        valuesSocio1.put("apellidoSocio", "Martínez")
        valuesSocio1.put("documentoSocio", 12345678)
        valuesSocio1.put("cantidadActividades", 2)
        db.insert(SocioTable.TABLE_NAME, null, valuesSocio1)

        val valuesSocio2 = ContentValues()
        valuesSocio2.put("idSocio", 101)
        valuesSocio2.put("nombreSocio", "Carlos")
        valuesSocio2.put("apellidoSocio", "López")
        valuesSocio2.put("documentoSocio", 23456789)
        valuesSocio2.put("cantidadActividades", 3)
        db.insert(SocioTable.TABLE_NAME, null, valuesSocio2)

        val valuesSocio3 = ContentValues()
        valuesSocio3.put("idSocio", 102)
        valuesSocio3.put("nombreSocio", "Maria")
        valuesSocio3.put("apellidoSocio", "Rodríguez")
        valuesSocio3.put("documentoSocio", 34567890)
        valuesSocio3.put("cantidadActividades", 1)
        db.insert(SocioTable.TABLE_NAME, null, valuesSocio3)

        val valuesSocio4 = ContentValues()
        valuesSocio4.put("idSocio", 103)
        valuesSocio4.put("nombreSocio", "Javier")
        valuesSocio4.put("apellidoSocio", "García")
        valuesSocio4.put("documentoSocio", 45678901)
        valuesSocio4.put("cantidadActividades", 2)
        db.insert(SocioTable.TABLE_NAME, null, valuesSocio4)

        val valuesSocio5 = ContentValues()
        valuesSocio5.put("idSocio", 104)
        valuesSocio5.put("nombreSocio", "Laura")
        valuesSocio5.put("apellidoSocio", "Pérez")
        valuesSocio5.put("documentoSocio", 56789012)
        valuesSocio5.put("cantidadActividades", 3)
        db.insert(SocioTable.TABLE_NAME, null, valuesSocio5)

        //Valores iniciales para la tabla Pagos
        val valuesPagos1 = ContentValues()
        valuesPagos1.put("idSocio", 100)
        valuesPagos1.put("importeTotal", 7000)
        valuesPagos1.put("fechaVencimiento", "2024-07-15")
        valuesPagos1.put("estado", 1)
        db.insert(PagosTable.TABLE_NAME, null, valuesPagos1)

        val valuesPagos2 = ContentValues()
        valuesPagos2.put("idSocio", 101)
        valuesPagos2.put("importeTotal", 10000)
        valuesPagos2.put("fechaVencimiento", "2024-07-07")
        valuesPagos2.put("estado", 0)
        db.insert(PagosTable.TABLE_NAME, null, valuesPagos2)

        val valuesPagos3 = ContentValues()
        valuesPagos3.put("idSocio", 102)
        valuesPagos3.put("importeTotal", 4000)
        valuesPagos3.put("fechaVencimiento", "2024-06-22")
        valuesPagos3.put("estado", 1)
        db.insert(PagosTable.TABLE_NAME, null, valuesPagos3)

        val valuesPagos4 = ContentValues()
        valuesPagos4.put("idSocio", 103)
        valuesPagos4.put("importeTotal", 7000)
        valuesPagos4.put("fechaVencimiento", "2024-11-19")
        valuesPagos4.put("estado", 1)
        db.insert(PagosTable.TABLE_NAME, null, valuesPagos4)

        val valuesPagos5 = ContentValues()
        valuesPagos5.put("idSocio", 104)
        valuesPagos5.put("importeTotal", 10000)
        valuesPagos5.put("fechaVencimiento", "2024-07-03")
        valuesPagos5.put("estado", 0)
        db.insert(PagosTable.TABLE_NAME, null, valuesPagos5)

        //valores iniciales para la tabla NoSocio
        val valuesNoSocio1 = ContentValues()
        valuesNoSocio1.put("nombreNS", "Alejandro")
        valuesNoSocio1.put("apellidoNS", "Ramirez")
        valuesNoSocio1.put("documentoNS", 67890123)
        db.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio1)

        val valuesNoSocio2 = ContentValues()
        valuesNoSocio2.put("nombreNS", "Sofía")
        valuesNoSocio2.put("apellidoNS", "Gutierrez")
        valuesNoSocio2.put("documentoNS", 78901234)
        db.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio2)

        val valuesNoSocio3 = ContentValues()
        valuesNoSocio3.put("nombreNS", "Pablo")
        valuesNoSocio3.put("apellidoNS", "Castro")
        valuesNoSocio3.put("documentoNS", 89012345)
        db.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio3)

        val valuesNoSocio4 = ContentValues()
        valuesNoSocio4.put("nombreNS", "Marta")
        valuesNoSocio4.put("apellidoNS", "Jimenez")
        valuesNoSocio4.put("documentoNS", 90123456)
        db.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio4)

        val valuesNoSocio5 = ContentValues()
        valuesNoSocio5.put("nombreNS", "Diego")
        valuesNoSocio5.put("apellidoNS", "Sanchez")
        valuesNoSocio5.put("documentoNS", 98765432)
        db.insert(NoSocioTable.TABLE_NAME, null, valuesNoSocio5)

        // Valores iniciales para la tabla Actividad
        val valuesActividad1 = ContentValues()
        valuesActividad1.put("nombreActividad", "Spinning")
        valuesActividad1.put("precioDiario", 500)
        valuesActividad1.put("cupoDisponible", 15)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad1)

        val valuesActividad2 = ContentValues()
        valuesActividad2.put("nombreActividad", "Funcional")
        valuesActividad2.put("precioDiario", 700)
        valuesActividad2.put("cupoDisponible", 10)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad2)

        val valuesActividad3 = ContentValues()
        valuesActividad3.put("nombreActividad", "Natacion")
        valuesActividad3.put("precioDiario", 800)
        valuesActividad3.put("cupoDisponible", 20)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad3)

        val valuesActividad4 = ContentValues()
        valuesActividad4.put("nombreActividad", "Yoga")
        valuesActividad4.put("precioDiario", 400)
        valuesActividad4.put("cupoDisponible", 12)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad4)

        val valuesActividad5 = ContentValues()
        valuesActividad5.put("nombreActividad", "Futbol")
        valuesActividad5.put("precioDiario", 600)
        valuesActividad5.put("cupoDisponible", 20)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad5)

        val valuesActividad6 = ContentValues()
        valuesActividad6.put("nombreActividad", "Pilates")
        valuesActividad6.put("precioDiario", 600)
        valuesActividad6.put("cupoDisponible", 0)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad6)

        val valuesActividad7 = ContentValues()
        valuesActividad7.put("nombreActividad", "Zumba")
        valuesActividad7.put("precioDiario", 350)
        valuesActividad7.put("cupoDisponible", 18)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad7)

        val valuesActividad8 = ContentValues()
        valuesActividad8.put("nombreActividad", "Escalada")
        valuesActividad8.put("precioDiario", 800)
        valuesActividad8.put("cupoDisponible", 6)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad8)

        val valuesActividad9 = ContentValues()
        valuesActividad9.put("nombreActividad", "Voley")
        valuesActividad9.put("precioDiario", 600)
        valuesActividad9.put("cupoDisponible", 12)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad9)

        val valuesActividad10 = ContentValues()
        valuesActividad10.put("nombreActividad", "Judo")
        valuesActividad10.put("precioDiario", 500)
        valuesActividad10.put("cupoDisponible", 10)
        db.insert(ActividadesTable.TABLE_NAME, null, valuesActividad10)

        // Insertar datos iniciales para la tabla Precios
        val valuesPrecios1 = ContentValues()
        valuesPrecios1.put("cantidadActividades", 0)
        valuesPrecios1.put("precio", 1000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios1)

        val valuesPrecios2 = ContentValues()
        valuesPrecios2.put("cantidadActividades", 1)
        valuesPrecios2.put("precio", 4000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios2)

        val valuesPrecios3 = ContentValues()
        valuesPrecios3.put("cantidadActividades", 2)
        valuesPrecios3.put("precio", 7000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios3)

        val valuesPrecios4 = ContentValues()
        valuesPrecios4.put("cantidadActividades", 3)
        valuesPrecios4.put("precio", 10000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios4)

        val valuesPrecios5 = ContentValues()
        valuesPrecios5.put("cantidadActividades", 4)
        valuesPrecios5.put("precio", 13000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios5)

        val valuesPrecios6 = ContentValues()
        valuesPrecios6.put("cantidadActividades", 5)
        valuesPrecios6.put("precio", 16000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios6)

        val valuesPrecios7 = ContentValues()
        valuesPrecios7.put("cantidadActividades", 6)
        valuesPrecios7.put("precio", 19000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios7)

        val valuesPrecios8 = ContentValues()
        valuesPrecios8.put("cantidadActividades", 7)
        valuesPrecios8.put("precio", 22000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios8)

        val valuesPrecios9 = ContentValues()
        valuesPrecios9.put("cantidadActividades", 8)
        valuesPrecios9.put("precio", 25000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios9)

        val valuesPrecios10 = ContentValues()
        valuesPrecios10.put("cantidadActividades", 9)
        valuesPrecios10.put("precio", 28000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios10)

        val valuesPrecios11 = ContentValues()
        valuesPrecios11.put("cantidadActividades", 10)
        valuesPrecios11.put("precio", 30000)
        db.insert(PreciosTable.TABLE_NAME, null, valuesPrecios11)
    }


}