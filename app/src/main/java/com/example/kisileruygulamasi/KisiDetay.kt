package com.example.kisileruygulamasi

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.kisileruygulamasi.entity.Kisiler


@Composable
fun KisiDetay(gelenKisi : Kisiler) {
    val tfKisiAd = remember {
        mutableStateOf("")
    }
    val tfKisiTel = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true){
        tfKisiAd.value = gelenKisi.kisiAd
        tfKisiTel.value = gelenKisi.kisiTel
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
                    Log.e("Kişi Güncelle", "${gelenKisi.kisiId} - $kisiAdi - $kisiTel")
                    localFocusManager.clearFocus()
                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Guncelle")
                }
            }
        }

    )
}