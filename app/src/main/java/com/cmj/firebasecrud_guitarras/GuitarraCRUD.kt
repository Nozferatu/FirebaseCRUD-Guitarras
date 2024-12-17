package com.cmj.firebasecrud_guitarras

import com.google.firebase.database.DatabaseReference

class GuitarraCRUD(
    var databaseRef: DatabaseReference
) {
    fun persistirGuitarra(guitarra: Guitarra){
        if(guitarra.key.isNotBlank()){

        }else{
            val idRef = databaseRef.child("guitarras").push().key!!
            guitarra.key = idRef

            databaseRef.child("guitarras").child(idRef).setValue(guitarra)
        }
    }
}