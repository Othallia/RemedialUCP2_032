package com.example.remedialucp2_032.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remedialucp2_032.R
import com.example.remedialucp2_032.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val rvBuku = findViewById<RecyclerView>(R.id.rvBuku)
        adapter = BukuAdapter()
        rvBuku.adapter = adapter
        rvBuku.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.semuaBuku.observe(this) { listBuku ->
            adapter.setData(listBuku)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fabTambah)
        fab.setOnClickListener {
            viewModel.tambahBukuContoh()
            Toast.makeText(this, "Buku contoh berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
        }

        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener { text ->
            viewModel.cariBuku(text.toString()).observe(this) { hasilCari ->
                adapter.setData(hasilCari)
            }
        }
    }
}