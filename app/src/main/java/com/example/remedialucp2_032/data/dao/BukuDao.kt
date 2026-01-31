package com.example.remedialucp2_032.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.remedialucp2_032.data.model.BukuFisik
import com.example.remedialucp2_032.data.model.BukuLengkap
import com.example.remedialucp2_032.data.model.BukuMaster

@Dao
interface BukuDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBukuMaster(buku: BukuMaster): Long

    @Insert
    suspend fun insertBukuFisik(bukuFisik: BukuFisik)

    @Transaction
    @Query("""
        SELECT 
            master.judul, 
            master.pengarang, 
            fisik.uniqueId, 
            fisik.status 
        FROM buku_master_table AS master
        INNER JOIN buku_fisik_table AS fisik ON master.id = fisik.bukuMasterId
        WHERE master.judul LIKE '%' || :keyword || '%' 
    """)
    fun cariBukuLengkap(keyword: String): LiveData<List<BukuLengkap>>
}