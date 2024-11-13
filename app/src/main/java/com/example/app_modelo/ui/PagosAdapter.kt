package com.example.app_modelo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.example.app_modelo.R
import com.example.app_modelo.data.model.Pagos

// class PagosAdapter(vencimientos01: Vencimientos01, pagosVencidosHoy: List<Pagos>) : ListAdapter {}
// class PagosAdapter(context: Context, private val pagosList: List<Pagos>) : ListAdapter<Pagos>(context, 0, pagosList) {
class PagosAdapter(context: Context, private val pagosList: List<Pagos>) : ListAdapter<Pagos>(context, 0, pagosList),
    ListAdapter {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_pago, parent, false)
        val pago = pagosList[position]

        val txtNroFactura = view.findViewById<TextView>(R.id.txtNroFactura)
        val txtImporteTotal = view.findViewById<TextView>(R.id.txtImporteTotal)
        val txtFechaVencimiento = view.findViewById<TextView>(R.id.txtFechaVencimiento)

        txtNroFactura.text = "Factura N°: ${pago.nroFactura}"
        txtImporteTotal.text = "Importe: $${pago.importeTotal}"
        txtFechaVencimiento.text = "Vencimiento: ${pago.fechaVencimiento}"

        return view
    }
}
