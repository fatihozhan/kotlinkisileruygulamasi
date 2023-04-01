package com.example.kisileruygulamasi.room

import androidx.room.*
import com.example.kisileruygulamasi.entity.Kisiler

@Dao
interface KisierDao {
    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler(): List<Kisiler>

    @Insert
    suspend fun kisiEkle(kisiler: Kisiler)

    @Update
    suspend fun kisiGuncelle(kisiler: Kisiler)

    @Delete
    suspend fun kisiSil(kisiler: Kisiler)


    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' ||:aramaKelimesi || '%'")
    suspend fun kisiAra(aramaKelimesi: String): List<Kisiler>


}