package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class KisiKayitSayfaViewModel(var application: Application): AndroidViewModel(application) {
    var krepo = KisilerDaoRepository(application)

    fun kayit(kisiAdi : String, kisiTel:String){
        krepo.kisiKayit(kisiAdi, kisiTel)
    }
}