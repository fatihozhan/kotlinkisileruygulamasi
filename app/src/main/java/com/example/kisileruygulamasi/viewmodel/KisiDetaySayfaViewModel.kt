package com.example.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class KisiDetaySayfaViewModel : ViewModel() {
    var krepo = KisilerDaoRepository()

    fun guncelle(kisi_id: Int, kisiAdi: String, kisiTel: String) {
        krepo.kisiGuncelle(kisi_id, kisiAdi, kisiTel)
    }
}