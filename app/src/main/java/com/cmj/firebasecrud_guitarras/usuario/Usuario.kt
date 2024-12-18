package com.cmj.firebasecrud_guitarras.usuario

import java.io.Serializable

data class Usuario(
    var key: String,
    var nombre: String,
    var pass: String
):Serializable {
    constructor() : this("", "", "")
}
