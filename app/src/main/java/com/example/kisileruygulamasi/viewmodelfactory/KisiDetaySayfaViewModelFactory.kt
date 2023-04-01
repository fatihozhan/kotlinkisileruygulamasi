package com.example.kisileruygulamasi.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kisileruygulamasi.viewmodel.KisiDetaySayfaViewModel

class KisiDetaySayfaViewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KisiDetaySayfaViewModel(application) as T
    }
}