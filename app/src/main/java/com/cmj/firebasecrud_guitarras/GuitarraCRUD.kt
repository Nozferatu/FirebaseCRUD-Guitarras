package com.cmj.firebasecrud_guitarras

import android.content.Context
import com.google.firebase.database.DatabaseReference

class GuitarraCRUD(
    var contexto: Context,
    var databaseRef: DatabaseReference
) {
    fun persistirGuitarra(guitarra: Guitarra){
        if(guitarra.key?.isNotBlank() == true){
            databaseRef.child("guitarras").child(guitarra.key!!).setValue(guitarra)

            hacerTostada(contexto, "Guitarra modificada")
        }else{
            val idRef = databaseRef.child("guitarras").push().key!!
            guitarra.key = idRef

            databaseRef.child("guitarras").child(idRef).setValue(guitarra)

            hacerTostada(contexto, "Guitarra creada")
        }
    }
}