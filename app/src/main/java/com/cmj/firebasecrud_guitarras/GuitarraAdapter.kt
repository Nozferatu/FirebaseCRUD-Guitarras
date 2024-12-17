package com.cmj.firebasecrud_guitarras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuitarraAdapter(private val listaGuitarras: MutableList<Guitarra>): RecyclerView.Adapter<GuitarraAdapter.GuitarraViewHolder>() {
    private lateinit var contexto: Context
    private var listadaFiltrada = listaGuitarras

    inner class GuitarraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenGuitarra)
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        val marca: TextView = itemView.findViewById(R.id.marca)
        val modelo: TextView = itemView.findViewById(R.id.modelo)
        val anio: TextView = itemView.findViewById(R.id.anio)
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
        holder.anio.text = guitarraActual.anio.toString()
        holder.precio.text = guitarraActual.precio.toString()
    }
}