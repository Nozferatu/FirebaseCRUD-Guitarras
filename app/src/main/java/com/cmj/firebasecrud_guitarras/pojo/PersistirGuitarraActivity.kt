package com.cmj.firebasecrud_guitarras.pojo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.R
import com.cmj.firebasecrud_guitarras.databinding.ActivityPersistirGuitarraBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.util.Calendar

class PersistirGuitarraActivity : AppCompatActivity() {
    private val contexto = this
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

        val guitarraIntent = intent.getSerializableExtra("guitarra") as Guitarra?

        //Cosas de Firebase
        databaseRef = Firebase.database.reference
        guitarraCRUD = GuitarraCRUD(contexto, databaseRef)

        with(binding){
            var guitarra: Guitarra

            if(guitarraIntent != null) {
                guitarra = guitarraIntent

                nombreET.setText(guitarra.nombre)
                descripcionET.setText(guitarra.descripcion)
                marcaET.setText(guitarra.marca)
                modeloET.setText(guitarra.modelo)
                rating.rating = guitarra.rating!!
                precioET.setText(guitarra.precio.toString())
            }

            botonPersistirGuitarra.setOnClickListener {
                val nombre = nombreET.text.toString()
                val descripcion = descripcionET.text.toString()
                val marca = marcaET.text.toString()
                val modelo = modeloET.text.toString()
                val rating = rating.rating
                val precio = precioET.text.toString()

                val calendar = Calendar.getInstance()
                //val dateFormat = SimpleDateFormat("yyyy-MM-dd") No funciona como yo quiero
                val fechaActual = StringBuilder()
                    .append(calendar.get(Calendar.YEAR)).append("-")
                    .append(calendar.get(Calendar.MONTH) + 1).append("-")
                    .append(calendar.get(Calendar.DATE)).toString()

                guitarra = Guitarra(
                    guitarraIntent?.key ?: "",
                    fechaActual,
                    nombre,
                    descripcion,
                    marca,
                    modelo,
                    rating,
                    precio.toFloat()
                )

                guitarraCRUD.persistirGuitarra(guitarra)
            }
        }
    }
}