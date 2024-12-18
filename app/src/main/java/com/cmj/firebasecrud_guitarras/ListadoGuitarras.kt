package com.cmj.firebasecrud_guitarras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmj.firebasecrud_guitarras.databinding.ActivityListadoGuitarrasBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListadoGuitarras : AppCompatActivity() {
    private lateinit var binding: ActivityListadoGuitarrasBinding

    private lateinit var recycler: RecyclerView
    private lateinit var lista: MutableList<Guitarra>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var adapter: GuitarraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListadoGuitarrasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Cosas de Firebase
        databaseRef = FirebaseDatabase.getInstance().reference

        with(binding){
            recycler = listaGuitarras
            lista = mutableListOf()

            databaseRef.child("guitarras")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        lista.clear()
                        snapshot.children.forEach{ child: DataSnapshot?->
                            val pojoGuitarra = child?.getValue(Guitarra::class.java)
                            lista.add(pojoGuitarra!!)
                        }

                        recycler.adapter?.notifyDataSetChanged()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        println(error.message)
                    }
                })


            adapter = GuitarraAdapter(lista, AccionGuitarraAdapter.BORRAR)
            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}