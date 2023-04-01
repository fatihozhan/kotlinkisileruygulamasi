package com.example.kisileruygulamasi

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.viewmodel.KisiDetaySayfaViewModel
import com.example.kisileruygulamasi.viewmodelfactory.KisiDetaySayfaViewModelFactory


@Composable
fun KisiDetay(gelenKisi : Kisiler) {
    val tfKisiAd = remember {mutableStateOf("")}
    val tfKisiTel = remember {mutableStateOf("")}
    val localFocusManager = LocalFocusManager.current

    val context = LocalContext.current
    val viewModel : KisiDetaySayfaViewModel = viewModel(
        factory = KisiDetaySayfaViewModelFactory(context.applicationContext as Application)
    )

    LaunchedEffect(key1 = true){
        tfKisiAd.value = gelenKisi.kisi_adi
        tfKisiTel.value = gelenKisi.kisi_tel
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kişi Detay") })
        },
        content = { it ->
            it
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = tfKisiAd.value, onValueChange = { tfKisiAd.value = it },
                    label = { Text(text = "Kişi Ad") }
                )
                TextField(value = tfKisiTel.value, onValueChange = { tfKisiTel.value = it },
                    label = { Text(text = "Kişi Tel") }
                )
                Button(onClick = {
                    val kisiAdi = tfKisiAd.value
                    val kisiTel = tfKisiTel.value
                    viewModel.guncelle(gelenKisi.kisi_id, kisiAdi, kisiTel)
                    localFocusManager.clearFocus()
                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Guncelle")
                }
            }
        }

    )
}