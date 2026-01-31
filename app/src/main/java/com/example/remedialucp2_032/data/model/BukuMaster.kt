package com.example.remedialucp2_032.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku_master_table")
data class BukuMaster(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val pengarang: String,
    val deskripsi: String,
    val kategoriId: Int
)