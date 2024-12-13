package com.cmj.firebasecrud_guitarras

import java.io.Serializable

data class Guitarra(
    var key: String,
    var nombre: String,
    var descripcion: String,
    var marca: String,
    var modelo: String,
    var anio: Int,
    var precio: Float
): Serializable
