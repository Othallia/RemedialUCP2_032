package com.example.remedialucp2_032.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori_table")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaKategori: String,
    val parentId: Int? = null
)