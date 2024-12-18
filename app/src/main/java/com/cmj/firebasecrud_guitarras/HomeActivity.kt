package com.cmj.firebasecrud_guitarras

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val contexto = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){
            botonCrearGuitarra.setOnClickListener {
                val intent = Intent(contexto, PersistirGuitarraActivity::class.java)
                startActivity(intent)
            }

            botonModificarGuitarra.setOnClickListener {
                val intent = Intent(contexto, ListadoGuitarras::class.java)
                intent.putExtra("accion", AccionGuitarraAdapter.MODIFICAR)

                startActivity(intent)
            }

            botonBorrarGuitarra.setOnClickListener {
                val intent = Intent(contexto, ListadoGuitarras::class.java)
                intent.putExtra("accion", AccionGuitarraAdapter.BORRAR)

                startActivity(intent)
            }
        }
    }
}