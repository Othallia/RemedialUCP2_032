package com.example.remedialucp2_032.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.remedialucp2_032.R
import com.example.remedialucp2_032.data.model.BukuMaster
import com.example.remedialucp2_032.data.model.Kategori
import com.example.remedialucp2_032.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TambahBukuActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_buku)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val etJudul = findViewById<TextInputEditText>(R.id.etJudul)
        val etPengarang = findViewById<TextInputEditText>(R.id.etPengarang)
        val etStok = findViewById<TextInputEditText>(R.id.etStok)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val judul = etJudul.text.toString()
            val pengarang = etPengarang.text.toString()
            val stokString = etStok.text.toString()

            if (judul.isEmpty() || pengarang.isEmpty() || stokString.isEmpty()) {
                Toast.makeText(this, "Isi semua data dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val stok = stokString.toInt()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    viewModel.repository.insertKategori(Kategori(id = 1, namaKategori = "Umum", parentId = null))
                } catch (e: Exception) {
                }

                val bukuBaru = BukuMaster(
                    judul = judul,
                    pengarang = pengarang,
                    deskripsi = "-",
                    kategoriId = 1
                )

                viewModel.repository.tambahBukuBaru(bukuBaru, stok)
            }

            runOnUiThread {
                Toast.makeText(this, "Buku Berhasil Disimpan!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}