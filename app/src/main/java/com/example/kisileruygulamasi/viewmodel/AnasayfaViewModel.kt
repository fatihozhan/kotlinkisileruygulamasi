package com.example.kisileruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class AnasayfaViewModel: ViewModel() {
    var krepo = KisilerDaoRepository()
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