import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.app_modelo.R
import com.example.app_modelo.data.model.Pagos

class PagosAdapter(context: Context, private val pagosList: List<Pagos>) : ArrayAdapter<Pagos>(context, 0, pagosList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_pago, parent, false)
        val pago = pagosList[position]

        val txtNroFactura = view.findViewById<TextView>(R.id.txtNroFactura)
        val txtIdSocio = view.findViewById<TextView>(R.id.txtIdSocio)
        val txtImporteTotal = view.findViewById<TextView>(R.id.txtImporteTotal)
        val txtFechaVencimiento = view.findViewById<TextView>(R.id.txtFechaVencimiento)
        val txtEstado = view.findViewById<TextView>(R.id.txtEstado)

        txtNroFactura.text = "Factura N°: ${pago.nroFactura}"
        txtIdSocio.text = "ID Socio: ${pago.idSocio}"
        txtImporteTotal.text = "Importe: $${pago.importeTotal}"
        txtFechaVencimiento.text = "Vencimiento: ${pago.fechaVencimiento}"
        txtEstado.text = "Estado: ${if (pago.estado) "Pagado" else "Pendiente"}"

        return view
    }
}
