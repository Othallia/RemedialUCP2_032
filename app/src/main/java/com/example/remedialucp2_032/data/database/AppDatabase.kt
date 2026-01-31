package com.example.remedialucp2_032.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.remedialucp2_032.data.dao.BukuDao
import com.example.remedialucp2_032.data.dao.KategoriDao
import com.example.remedialucp2_032.data.model.BukuFisik
import com.example.remedialucp2_032.data.model.BukuMaster
import com.example.remedialucp2_032.data.model.Kategori

@Database(
    entities = [
        Kategori::class,
        BukuMaster::class,
        BukuFisik::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kategoriDao(): KategoriDao
    abstract fun bukuDao(): BukuDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "perpustakaan_kampus.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}