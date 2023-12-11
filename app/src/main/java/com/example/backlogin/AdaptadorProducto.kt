package com.example.backlogin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backlogin.databinding.ItemRvProductosBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdaptadorProducto(
    private val context: Context,
    private val tvCantProductos: TextView,
    private val btnVerCarro: FloatingActionButton,
    private val listaProductos: List<Producto>,
    private val carroCompras: MutableSet<Producto>
) : RecyclerView.Adapter<AdaptadorProducto.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRvProductosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto) {
            binding.apply {
                tvNomProducto.text = producto.nomProducto
                tvDescripcion.text = producto.descriProducto
                tvPrecio.text = producto.precio.toString()

                cbCarro.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        tvCantProductos.text = "${tvCantProductos.text.toString().toInt() + 1}"
                        carroCompras.add(producto)
                    } else {
                        tvCantProductos.text = "${tvCantProductos.text.toString().toInt() - 1}"
                        carroCompras.remove(producto)
                    }
                }

                btnVerCarro.setOnClickListener {
                    val intent = Intent(context, CarroComprasActivity::class.java)
                    intent.putExtra("carro_compras", ArrayList(carroCompras))
                    context.startActivity(intent)
                }

                rvImagenes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val adaptadorImagenes = AdaptadorImagenes(producto.listaImagenes)
                rvImagenes.adapter = adaptadorImagenes
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRvProductosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaProductos[position])
    }

    override fun getItemCount(): Int = listaProductos.size
}

class AdaptadorImagenes(private val imagenes: List<Int>) : RecyclerView.Adapter<AdaptadorImagenes.ImagenViewHolder>() {

    class ImagenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imagenProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_producto, parent, false)
        return ImagenViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImagenViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(imagenes[position])
            .placeholder(R.drawable.carrito)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = imagenes.size
}

//Codigo Antiguo
//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val tvNomProducto = itemView.findViewById(R.id.tvNomProducto) as TextView
//        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
//        val cbCarro = itemView.findViewById(R.id.cbCarro) as CheckBox
//        val tvPrecio = itemView.findViewById(R.id.tvPrecio) as TextView
//
//    }
//
//    class ImagenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val imageView:ImageView = itemView.findViewById(R.id.imagenProducto)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        var vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_productos, parent, false)
//        return ViewHolder(vista)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val producto = listaProductos[position]
//
//        holder.tvNomProducto.text = producto.nomProducto
//        holder.tvDescripcion.text = producto.descriProducto
//        holder.tvPrecio.text = producto.precio.toString()
//
//        holder.cbCarro.setOnCheckedChangeListener { compoundButton, b ->
//            if (holder.cbCarro.isChecked) {
//                tvCantProductos.text = "${Integer.parseInt(tvCantProductos.text.toString().trim()) + 1}"
//                carroCompras.add(listaProductos[position])
//            }else{
//                tvCantProductos.text = "${Integer.parseInt(tvCantProductos.text.toString().trim()) - 1}"
//                carroCompras.remove(listaProductos[position])
//            }
//        }
//        btnVerCarro.setOnClickListener{
//            val intent = Intent(context, CarroComprasActivity::class.java)
//            intent.putExtra("carro_compras", carroCompras)
//            context.startActivity(intent)
//        }
//
//        val rvImagenes = holder.itemView.findViewById<RecyclerView>(R.id.rvImagenes)
//        val layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
//        rvImagenes.layoutManager = layoutManager
//
//        val adaptadorImagenes = AdaptadorImagenes(producto.listaImagenes)
//        rvImagenes.adapter=adaptadorImagenes
//
//
////        Glide.with(holder.itemView.context)
////            .load(producto.listaImagenes)
////            .placeholder(R.drawable.carrito)
////            .into(holder.itemView as ImageView)
//
//    } class AdaptadorImagenes(private val imagenes: List<String>) : RecyclerView.Adapter<ImagenViewHolder>(){
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_producto, parent, false)
//            return ImagenViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ImagenViewHolder, position: Int){
//
//            Glide.with(holder.itemView.context)
//                .load(imagenes[position])
//                .placeholder(R.drawable.carrito)
//                .into(holder.imageView)
//        }
//
//        override fun getItemCount(): Int = imagenes.size
//    }
//
//
//    override fun getItemCount(): Int {
//        return listaProductos.size
//    }
//}