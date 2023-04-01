package com.example.kisileruygulamasi.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kisileruygulamasi.viewmodel.KisiKayitSayfaViewModel

class KisiKayitSayfaVİewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KisiKayitSayfaViewModel(application) as T
    }
}