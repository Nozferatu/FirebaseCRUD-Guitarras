package com.cmj.firebasecrud_guitarras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.databinding.ActivityPersistirGuitarraBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class PersistirGuitarraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersistirGuitarraBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var guitarraCRUD: GuitarraCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPersistirGuitarraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Cosas de Firebase
        databaseRef = Firebase.database.reference
        guitarraCRUD = GuitarraCRUD(databaseRef)

        with(binding){
            botonPersistirGuitarra.setOnClickListener {
                val nombre = nombreET.text.toString()
                val descripcion = descripcionET.text.toString()
                val marca = marcaET.text.toString()
                val modelo = modeloET.text.toString()
                val anio = anioET.text.toString()
                val precio = precioET.text.toString()

                val guitarra = Guitarra(
                    "",
                    nombre,
                    descripcion,
                    marca,
                    modelo,
                    anio.toInt(),
                    precio.toFloat()
                )

                guitarraCRUD.persistirGuitarra(guitarra)
            }
        }
    }
}