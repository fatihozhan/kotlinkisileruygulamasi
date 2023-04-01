package com.example.kisileruygulamasi.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KisilerDaoRepository(var application: Application) {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    var vt: Veritabani

    init {
        kisilerListesi = MutableLiveData<List<Kisiler>>()
        vt = Veritabani.veritabaninaErisim(application)!!
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().tumKisiler()
        }
    }

    fun kisiAra(aramaKelimesi: String) {
        val job : Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = vt.kisilerDao().kisiAra(aramaKelimesi)
        }
    }

    fun kisiKayit(kisiAdi: String, kisiTel: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0, kisiAdi, kisiTel)
            vt.kisilerDao().kisiEkle(yeniKisi)
        }

    }

    fun kisiGuncelle(kisi_id: Int, kisiAdi: String, kisiTel: String) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisi_id, kisiAdi, kisiTel)
            vt.kisilerDao().kisiGuncelle(guncellenenKisi)
        }
    }

    fun kisiSil(kisi_id: Int) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(kisi_id, "", "")
            vt.kisilerDao().kisiSil(silinenKisi)
            tumKisileriAl()
        }
    }
}