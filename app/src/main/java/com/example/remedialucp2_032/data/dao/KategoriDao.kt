package com.example.remedialucp2_032.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.remedialucp2_032.data.model.Kategori

@Dao
interface KategoriDao {
    @Insert
    suspend fun insertKategori(kategori: Kategori)

    @Query("SELECT * FROM kategori_table ORDER BY namaKategori ASC")
    fun getAllKategori(): LiveData<List<Kategori>>

    @Query("SELECT * FROM kategori_table WHERE parentId = :idInduk")
    fun getSubKategori(idInduk: Int): LiveData<List<Kategori>>
}