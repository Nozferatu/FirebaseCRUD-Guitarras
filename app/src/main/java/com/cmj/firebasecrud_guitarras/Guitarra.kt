package com.cmj.firebasecrud_guitarras

import java.io.Serializable

data class Guitarra(
    var key: String? = "",
    var fechaCreacion: String? = "2000-01-01",
    var nombre: String? = "",
    var descripcion: String? = "",
    var marca: String? = "",
    var modelo: String? = "",
    var rating: Float? = 5f,
    var precio: Float? = 100f
): Serializable
