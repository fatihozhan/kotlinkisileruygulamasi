package com.example.kisileruygulamasi.entity


import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Kisiler(
    var kisi_id: String? = "",
    var kisi_adi: String? = "",
    var kisi_tel: String? = ""
) {
}