package com.example.remedialucp2_032.data

import androidx.lifecycle.LiveData
import com.example.remedialucp2_032.data.dao.BukuDao
import com.example.remedialucp2_032.data.dao.KategoriDao
import com.example.remedialucp2_032.data.model.BukuFisik
import com.example.remedialucp2_032.data.model.BukuLengkap
import com.example.remedialucp2_032.data.model.BukuMaster
import com.example.remedialucp2_032.data.model.Kategori

class PerpusRepository(
    private val kategoriDao: KategoriDao,
    private val bukuDao: BukuDao
) {

    val allKategori: LiveData<List<Kategori>> = kategoriDao.getAllKategori()

    suspend fun insertKategori(kategori: Kategori) {
        kategoriDao.insertKategori(kategori)
    }

    fun cariBuku(keyword: String): LiveData<List<BukuLengkap>> {
        return bukuDao.cariBukuLengkap(keyword)
    }

    suspend fun tambahBukuBaru(bukuMaster: BukuMaster, stokAwal: Int) {

        val masterId = bukuDao.insertBukuMaster(bukuMaster)

        for (i in 1..stokAwal) {
            val bukuFisik = BukuFisik(
                bukuMasterId = masterId.toInt(),
                uniqueId = "LIB-" + masterId + "-" + i,
                status = "TERSEDIA",
                kondisi = "BAGUS"
            )
            bukuDao.insertBukuFisik(bukuFisik)
        }
    }
}