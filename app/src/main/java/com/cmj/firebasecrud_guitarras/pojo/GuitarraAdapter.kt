package com.cmj.firebasecrud_guitarras.pojo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.cmj.firebasecrud_guitarras.R
import com.cmj.firebasecrud_guitarras.hacerTostada
import com.google.firebase.database.FirebaseDatabase

class GuitarraAdapter(private val listaGuitarras: MutableList<Guitarra>, val accion: AccionGuitarraAdapter): RecyclerView.Adapter<GuitarraAdapter.GuitarraViewHolder>() {
    private lateinit var contexto: Context
    private var listadaFiltrada = listaGuitarras

    inner class GuitarraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenGuitarra)
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val marca: TextView = itemView.findViewById(R.id.marca)
        val modelo: TextView = itemView.findViewById(R.id.modelo)
        val rating: RatingBar = itemView.findViewById(R.id.rating)
        val precio: TextView = itemView.findViewById(R.id.precio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuitarraViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_guitarra, parent, false)
        contexto = parent.context
        return GuitarraViewHolder(item)
    }

    override fun getItemCount() = listadaFiltrada.size

    override fun onBindViewHolder(holder: GuitarraViewHolder, position: Int) {
        val guitarraActual = listadaFiltrada[position]
        holder.nombre.text = guitarraActual.nombre
        holder.descripcion.text = guitarraActual.descripcion
        holder.marca.text = guitarraActual.marca
        holder.modelo.text = guitarraActual.modelo
        holder.rating.rating = guitarraActual.rating!!
        holder.precio.text = guitarraActual.precio.toString()

        holder.itemView.setOnClickListener {
            val databaseRef= FirebaseDatabase.getInstance().reference

            when(accion){
                AccionGuitarraAdapter.BORRAR -> {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(contexto)
                    builder
                        .setTitle("Confirmación")
                        .setMessage("¿Está seguro de que quiere borrar esta guitarra?")
                        .setPositiveButton("Sí") { _, _ ->
                            listadaFiltrada.removeAt(position)
                            databaseRef.child("guitarras").child(guitarraActual.key!!).removeValue()
                            hacerTostada(contexto, "Guitarra borrada")

                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, listadaFiltrada.size)
                        }
                        .setNegativeButton("No") { _, _ ->
                            hacerTostada(contexto, "Cancelado")
                        }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
                AccionGuitarraAdapter.MODIFICAR -> {
                    val intent = Intent(contexto, PersistirGuitarraActivity::class.java)
                    intent.putExtra("guitarra", guitarraActual)

                    contexto.startActivity(intent)
                }
            }
        }
    }
}