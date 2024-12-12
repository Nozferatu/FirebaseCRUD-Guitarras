package com.cmj.firebasecrud_guitarras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cmj.firebasecrud_guitarras.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class CrearCuentaActivity : AppCompatActivity() {
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
    }
}