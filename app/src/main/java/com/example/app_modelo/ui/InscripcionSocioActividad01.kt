package com.example.app_modelo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_modelo.R
import com.example.app_modelo.R.id.btnBuscarSocioInscripcion
import com.example.app_modelo.R.id.btnVolverInscripcionAct
import com.example.app_modelo.data.model.NoSocio
import com.example.app_modelo.data.model.Socio
import com.example.app_modelo.data.repository.ActividadesRepository
import com.example.app_modelo.data.repository.NoSocioRepository
import com.example.app_modelo.data.repository.SocioRepository
import com.google.android.material.textfield.TextInputEditText

class InscripcionSocioActividad01 : AppCompatActivity() {

    private lateinit var socioRepository: SocioRepository
    private lateinit var noSocioRepository: NoSocioRepository
    private lateinit var actividadesRepository: ActividadesRepository
    private lateinit var lblDocumento: TextInputEditText
    private lateinit var actvActividades: AutoCompleteTextView
    private lateinit var btnInscripcionSocioAct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion_socio_actividad01)

        socioRepository = SocioRepository(this)
        noSocioRepository = NoSocioRepository(this)
        actividadesRepository = ActividadesRepository(this)
        lblDocumento = findViewById(R.id.lblDocumento)

        configuracionAutoCompleteTextView()

        val btnBuscarSocio = findViewById<Button>(btnBuscarSocioInscripcion)
        btnInscripcionSocioAct = findViewById(R.id.btnInscripcionSocioAct)

        //este metodo busca el socio en la base de datos con el dni ingresado
        btnBuscarSocio.setOnClickListener{
            val documento = lblDocumento.text.toString()
            if (documento.isNotEmpty()) {
                val dni = documento.toInt()
                val socioEncontrado = socioRepository.obtenerSocioPorDocumento(dni)
                val noSocioEncontrado = noSocioRepository.obtenerNoSocioPorDocumento(dni)

                if (socioEncontrado != null) {
                    val nombreSocio = socioEncontrado.nombreSocio
                    Toast.makeText(this, "Nombre del socio: $nombreSocio", Toast.LENGTH_SHORT).show()
                } else if (noSocioEncontrado != null){
                    val nombreNS = noSocioEncontrado.nombreNS
                    Toast.makeText(this, "Nombre del no socio: $nombreNS", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Socio no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingresa un DNI", Toast.LENGTH_SHORT).show()
            }
        }

        btnInscripcionSocioAct.setOnClickListener{
            val documento = lblDocumento.text.toString().trim()
            val actividadSeleccionada = actvActividades.text.toString().trim()

            if (documento.isNotEmpty() && actividadSeleccionada.isNotEmpty()) {
                val dni = documento.toInt()

                // Buscar socio/no socio por DNI
                val socioEncontrado = socioRepository.obtenerSocioPorDocumento(dni)
                val noSocioEncontrado = noSocioRepository.obtenerNoSocioPorDocumento(dni)

                // Verificar que se haya ingresado un documento registrado como socio o no socio
                if (socioEncontrado == null && noSocioEncontrado == null) {
                    Toast.makeText(this, "Documento no registrado", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Buscar la Actividad por nombre
                val actividad = actividadesRepository.obtenerActividad(actividadSeleccionada)

                // Verificar que la actividad exista
                if (actividad == null) {
                    Toast.makeText(this, "Actividad no encontrada", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Verificar que la actividad tenga cupos disponibles
                if (actividad.cupoDisponible <= 0) {
                    Toast.makeText(this, "No hay cupos disponibles para esta actividad", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Si el Documento ingresado corresponde a un socio:
                if (socioEncontrado != null) {
                    // Verificar si alcanzó el límite de actividades permitidas (10)
                    val limiteAlcanzado = socioEncontrado.cantidadActividades >= 10
                    if (limiteAlcanzado) {
                        Toast.makeText(this, "Se alcanzó el límite de actividades", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    // Incrementar cantidadActividades del socio
                    val socioActualizado = socioRepository.sumarActividad(socioEncontrado.idSocio)
                    // Restar uno al cupo disponible de la actividad
                    val actividadActualizada = actividadesRepository.restarCupo(actividad.idActividad)
                    if (socioActualizado && actividadActualizada) {
                        Toast.makeText(this, "Socio inscrito a la actividad correctamente", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                        return@setOnClickListener
                    }
                } else {
                    val actividadActualizada = actividadesRepository.restarCupo(actividad.idActividad)
                    if (actividadActualizada) {
                        Toast.makeText(this, "No Socio inscrito a la actividad correctamente", Toast.LENGTH_SHORT).show()
                        limpiarCampos()

                        // Emitir comprobante
                        val IntentComprobante = Intent(this, activity_comprobante_pago::class.java)
                        IntentComprobante.putExtra("DOCUMENTO", documento)
                        IntentComprobante.putExtra("ACTIVIDAD", actividadSeleccionada)
                        startActivity(IntentComprobante)
                        finish()
                    }
                }
            }
//                if (socioEncontrado != null) {
//                    // Buscar actividad por nombre
//                    val actividad = actividadesRepository.obtenerTodasLasActividades()
//                        .find { it.nombreActividad == actividadSeleccionada }
//
//                    if (actividad != null) {
//                        // Verificar si la actividad tiene cupos disponibles
//                        if (actividad.cupoDisponible > 0) {
//
//                            // Verificar si el socio llegó al límite de actividades permitidas (10)
//                            val limiteAlcanzado = socioEncontrado.cantidadActividades >= 10
//
//                            if (limiteAlcanzado) {
//                                Toast.makeText(this, "Se alcanzó el límite de actividades", Toast.LENGTH_SHORT).show()
//                                return@setOnClickListener
//                            }
//
//                            // Incrementar cantidadActividades del socio
//                            val socioActualizado = socioRepository.sumarActividad(socioEncontrado.idSocio)
//
//                            // Restar uno al cupo disponible de la actividad
//                            val actividadActualizada = actividadesRepository.restarCupo(actividad.idActividad)
//
//                            if (socioActualizado && actividadActualizada) {
//                                Toast.makeText(this, "Socio inscrito a la actividad correctamente", Toast.LENGTH_SHORT).show()
//                                limpiarCampos()
//                            } else {
//                                Toast.makeText(this, "Hubo un error al inscribir al socio", Toast.LENGTH_SHORT).show()
//                            }
//                        } else {
//                            Toast.makeText(this, "No hay cupos disponibles para esta actividad", Toast.LENGTH_SHORT).show()
//                        }
//                    } else {
//                        Toast.makeText(this, "Actividad no encontrada", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    val noSocioEncontrado = noSocioRepository.obtenerNoSocioPorDocumento(dni)
//                    if (noSocioEncontrado != null) {
//
//                    } else {
//                        Toast.makeText(this, "Socio no encontrado", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Por favor ingresa todos los campos", Toast.LENGTH_SHORT).show()
//            }
        }

        val btnVolver = findViewById<Button>(btnVolverInscripcionAct)
        btnVolver.setOnClickListener {
            regresarMenuPrincipal()
        }
    }

    private fun obtenerNombresActividades(): List<String> {
        val listaDeActividades = actividadesRepository.obtenerTodasLasActividades()
        return listaDeActividades.map { it.nombreActividad }
    }

    //este metodo llena la lista desplegable con todas las actividades
    private fun configuracionAutoCompleteTextView(){
        actvActividades = findViewById(R.id.actvActividades)

        val nombresActividades = obtenerNombresActividades()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            nombresActividades
        )
        actvActividades.setAdapter(adapter)
    }

    private fun Inscribir(socio: Socio?, noSocio: NoSocio?, actividadSeleccionada: String) {
        if (socio == null && noSocio == null) {
            Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            return
        }

        val actividad = actividadesRepository.obtenerActividad(actividadSeleccionada)
        if (actividad == null) {
            Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            return
        }

        if (actividad.cupoDisponible <= 0) {
            Toast.makeText(this, "No hay cupos disponibles para esta actividad", Toast.LENGTH_SHORT).show()
            return
        }



        if (socio != null) {

        }
    }

    private fun limpiarCampos(){

        lblDocumento.setText("")
        actvActividades.setText("", false)
        actvActividades.clearListSelection()
    }

    private fun regresarMenuPrincipal(){
        val intentarCancelar = Intent(this, MenuPrincipal::class.java)
        startActivity(intentarCancelar)
        finish()
    }

}