package com.example.backlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backlogin.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity(){


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptadorProducto

    var listaProductos = ArrayList<Producto>()
    var carroCompras = mutableSetOf<Producto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        agregarProductos()

        setupRecyclerView()
    }

    fun setupRecyclerView(){
        binding.rvListaProductos.layoutManager = LinearLayoutManager(this)
        adapter = AdaptadorProducto(this, binding.tvCantProductos, binding.carritoFaButton, listaProductos, carroCompras)
        binding.rvListaProductos.adapter = adapter
    }

    fun agregarProductos(){
        listaProductos.add(Producto("1", "Filtros de Combustible", "Se utilizan para eliminar contaminantes del combustible ", 100.0, 1, listOf(R.drawable.producto1)))
        listaProductos.add(Producto("2", "Filtro de combustible", "Se utilizan para eliminar contaminantes del combustible", 99.99, 1, listOf(R.drawable.producto2)))
        listaProductos.add(Producto("3", "Filtros de combustible", "Se utilizan para eliminar contaminantes del combustible", 149.99, 1, listOf(R.drawable.producto3)))
        listaProductos.add(Producto("4", "Filtro de combustible de banjo", "Se utilizan para eliminar contaminantes del combustible", 199.99, 1, listOf(R.drawable.producto4)))
        listaProductos.add(Producto("5", "Filtro de combustible de línea", "Se utilizan para eliminar contaminantes del combustible", 129.99, 1, listOf(R.drawable.producto5)))
    }
}