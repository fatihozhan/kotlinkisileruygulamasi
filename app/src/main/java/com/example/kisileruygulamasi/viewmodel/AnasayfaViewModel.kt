package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class AnasayfaViewModel(var application: Application): AndroidViewModel(application) {
    var krepo = KisilerDaoRepository(application)
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisileriYukle()
        kisilerListesi = krepo.kisileriGetir()
    }
    fun kisileriYukle(){
        krepo.tumKisileriAl()
    }

    fun ara(aramaKelimesi : String){
        krepo.kisiAra(aramaKelimesi)
    }
    fun sil(kisiSil:Int){
        krepo.kisiSil(kisiSil)
    }
}