package com.example.backlogin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCarroCompras (
    var tvTotal: TextView,
    var carroCompras: ArrayList<Producto>

): RecyclerView.Adapter<AdaptadorCarroCompras.ViewHolder>(){

    var total: Double = 0.0
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNomProducto = itemView.findViewById(R.id.tvNomProducto) as TextView
        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        val tvPrecio = itemView.findViewById(R.id.tvPrecio) as TextView

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_carro_compras, parent, false)

        total = 0.0

        carroCompras.forEach{
            total += it.precio
        }

        tvTotal.text = "$$total"
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = carroCompras[position]

        holder.tvNomProducto.text = producto.nomProducto
        holder.tvDescripcion.text = producto.descriProducto
        holder.tvPrecio.text = "$${producto.precio}"

        holder.itemView.findViewById<ImageView>(R.id.btnMenos).setOnClickListener{
            decrementarCantidad(holder, producto)
        }

        holder.itemView.findViewById<ImageView>(R.id.btnMas).setOnClickListener{
            incrementarCantidad(holder, producto)
        }
    }

    private fun incrementarCantidad(holder: ViewHolder, producto: Producto){

        producto.cantidad += 1

        holder.itemView.findViewById<TextView>(R.id.tvCantidad).text = producto.cantidad.toString()

        actualizarPrecioTotal()
    }

    override fun getItemCount(): Int {
       return carroCompras.size
    }

    private fun decrementarCantidad(holder: ViewHolder, producto: Producto){
        if (producto.cantidad > 1){
            producto.cantidad -=1

            holder.itemView.findViewById<TextView>(R.id.tvCantidad).text = producto.cantidad.toString()

            actualizarPrecioTotal()
        }
    }

    fun calcularMontoTotal(): Double{

        var precioTotal = 0.0
        for (producto in carroCompras){
            precioTotal += producto.precio * producto.cantidad
        }
        return precioTotal
    }

    private fun actualizarPrecioTotal(){
        val precioTotal = calcularMontoTotal()
        tvTotal.text = precioTotal.toString()

    }
}