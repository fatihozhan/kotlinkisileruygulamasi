package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class KisiDetaySayfaViewModel(var application: Application): AndroidViewModel(application) {
    var krepo = KisilerDaoRepository(application)

    fun guncelle(kisi_id: Int, kisiAdi: String, kisiTel: String) {
        krepo.kisiGuncelle(kisi_id, kisiAdi, kisiTel)
    }
}