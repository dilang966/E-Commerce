package com.example.backlogin

import java.io.Serializable

class Producto (
    var idProducto: String,
    var nomProducto: String,
    var descriProducto: String,
    var precio: Double,
    var cantidad: Int = 1,
    val listaImagenes: List<Int>

): Serializable