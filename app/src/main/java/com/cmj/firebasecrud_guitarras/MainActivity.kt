package com.cmj.firebasecrud_guitarras

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.databinding.ActivityMainBinding
import com.cmj.firebasecrud_guitarras.usuario.CrearCuentaActivity
import com.cmj.firebasecrud_guitarras.usuario.Usuario
import com.cmj.firebasecrud_guitarras.usuario.UsuarioCRUD
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    private var contexto = this
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var usuarioCRUD: UsuarioCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Cosas de Firebase
        databaseRef = Firebase.database.reference
        usuarioCRUD = UsuarioCRUD(contexto, databaseRef)

        //Usuario de prueba
        /*
        var usuarioPrueba = Usuario("", "Amo", "lmao".toSHA256())
        val idRef = usuarioCRUD.registrarUsuario(usuarioPrueba)
        usuarioPrueba.key = idRef

        println(usuarioPrueba)*/

        with(binding){
            val nombre = nombreET.text
            val pass = passET.text

            botonCrearUsuario.setOnClickListener {
                val intent = Intent(contexto, CrearCuentaActivity::class.java)
                startActivity(intent)
            }

            botonLogin.setOnClickListener {
                if(!nombre.isNullOrEmpty()){
                    if(!pass.isNullOrEmpty()){
                        val sesion = Usuario("", nombre.toString().trim(), pass.toString().toSHA256())

                        usuarioCRUD.buscarUsuario(sesion, true) { usuario ->
                            if (usuario != null) {
                                hacerTostada(contexto, "Inicio de sesión correcto")

                                val intent = Intent(contexto, HomeActivity::class.java)
                                intent.putExtra("sesion", usuario)

                                startActivity(intent)
                            }
                        }
                    }else hacerTostada(contexto, "La contraseña está vacía")
                }else hacerTostada(contexto, "El nombre está vacío")
            }
        }
    }
}