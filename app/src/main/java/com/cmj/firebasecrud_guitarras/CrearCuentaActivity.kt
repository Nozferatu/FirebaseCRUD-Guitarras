package com.cmj.firebasecrud_guitarras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.databinding.ActivityCrearCuentaBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class CrearCuentaActivity : AppCompatActivity() {
    private var contexto = this
    private lateinit var binding: ActivityCrearCuentaBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var usuarioCRUD: UsuarioCRUD

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Cosas de Firebase
        databaseRef = Firebase.database.reference
        usuarioCRUD = UsuarioCRUD(contexto, databaseRef)

        with(binding){
            val nombreET = nombreET.text
            val passET = passET.text
            val passRepeET = passRepeET.text

            botonCrearCuenta.setOnClickListener {
                if(!nombreET.isNullOrEmpty()){
                    if(!passET.isNullOrEmpty()){
                        if(!passRepeET.isNullOrEmpty()){
                            val nombre = nombreET.toString().trim()
                            val pass = passET.toString().trim()
                            val passRepe = passRepeET.toString().trim()

                            if(pass.equals(passRepe)){
                                val usuarioNuevo = Usuario("", nombre, pass.toSHA256())

                                usuarioCRUD.usuarioExiste(usuarioNuevo) { usuarioExiste ->
                                    if(usuarioExiste != null){
                                        if(!usuarioExiste){
                                            usuarioCRUD.registrarUsuario(usuarioNuevo)
                                            hacerTostada(contexto, "Usuario creado. Vuelva atrás para iniciar sesión")
                                        }else hacerTostada(contexto, "El usuario ya existe")
                                    }
                                }
                            }else hacerTostada(contexto, "La contraseña no coincide")
                        }else hacerTostada(contexto, "Se debe confirmar la contraseña")
                    }else hacerTostada(contexto, "La contraseña no puede estar vacía")
                }else hacerTostada(contexto, "El nombre no puede estar vacío")
            }
        }
    }
}