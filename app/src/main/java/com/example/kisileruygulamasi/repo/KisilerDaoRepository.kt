package com.example.kisileruygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.Kisiler

class KisilerDaoRepository {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
        val liste = mutableListOf<Kisiler>()
        val k1 = Kisiler(1, "Ahmet", "111111")
        val k2 = Kisiler(2, "Zeynep", "22222")
        liste.add(k1)
        liste.add(k2)
        kisilerListesi.value = liste
    }

    fun kisiAra(aramaKelimesi: String) {
        Log.e("arama", aramaKelimesi)
    }

    fun kisiKayit(kisiAdi: String, kisiTel: String) {
        Log.e("kisikayit", "$kisiAdi - $kisiTel")

    }

    fun kisiGuncelle(kisi_id: Int, kisiAdi: String, kisiTel: String) {
        Log.e("Kişi", "$kisiAdi - $kisi_id - $kisiTel")
    }

    fun kisiSil(kisi_id: Int) {
        Log.e("Kişi", "$kisi_id")
    }
}