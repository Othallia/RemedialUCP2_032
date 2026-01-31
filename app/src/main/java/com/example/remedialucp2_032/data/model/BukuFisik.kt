package com.example.remedialucp2_032.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku_fisik_table")
data class BukuFisik(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val bukuMasterId: Int,
    val uniqueId: String,
    val status: String,
    val kondisi: String
)