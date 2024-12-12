package com.cmj.firebasecrud_guitarras

import android.content.Context
import android.widget.Toast
import java.security.MessageDigest

fun hacerTostada(contexto: Context, mensaje: String, tiempo: Int = Toast.LENGTH_SHORT){
    Toast.makeText(
        contexto,
        mensaje,
        tiempo
    ).show()
}

fun String.toSHA256(): String{
    val HEX_CHARS = "0123456789ABCDEF"
    val digest = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())

    return digest.joinToString(
        separator = "",
        transform = { a ->
            String(
                charArrayOf(
                    HEX_CHARS[a.toInt() shr 4 and 0X0f],
                    HEX_CHARS[a.toInt() and 0x0f]
                )
            )
        }
    )
}
