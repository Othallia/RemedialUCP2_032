package com.example.remedialucp2_032.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_032.data.database.AppDatabase
import com.example.remedialucp2_032.data.model.BukuMaster
import com.example.remedialucp2_032.data.model.Kategori
import com.example.remedialucp2_032.data.model.BukuLengkap
import com.example.remedialucp2_032.data.PerpusRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository: PerpusRepository

    val semuaBuku: LiveData<List<BukuLengkap>>

    init {
        val database = AppDatabase.getDatabase(application)
        val bukuDao = database.bukuDao()
        val kategoriDao = database.kategoriDao()
        repository = PerpusRepository(kategoriDao, bukuDao)

        semuaBuku = repository.cariBuku("")
    }

    fun cariBuku(keyword: String): LiveData<List<BukuLengkap>> {
        return repository.cariBuku(keyword)
    }

    fun tambahBukuContoh() = viewModelScope.launch {
        repository.insertKategori(Kategori(namaKategori = "Fiksi", parentId = null))
        val bukuBaru = BukuMaster(
            judul = "Laskar Pelangi",
            pengarang = "Andrea Hirata",
            deskripsi = "Kisah anak Belitong",
            kategoriId = 1
        )
        repository.tambahBukuBaru(bukuBaru, 3)
    }
}