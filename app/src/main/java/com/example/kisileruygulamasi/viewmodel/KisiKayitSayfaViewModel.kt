package com.example.kisileruygulamasi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class KisiKayitSayfaViewModel: ViewModel() {
    var krepo = KisilerDaoRepository()

    fun kayit(kisiAdi : String, kisiTel:String){
        krepo.kisiKayit(kisiAdi, kisiTel)
    }
}