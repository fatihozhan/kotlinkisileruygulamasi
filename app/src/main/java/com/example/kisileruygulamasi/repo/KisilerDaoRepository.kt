package com.example.kisileruygulamasi.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.CRUDCevap
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.entity.KisilerCevap
import com.example.kisileruygulamasi.retrofit.ApiUtlils
import com.example.kisileruygulamasi.retrofit.KisilerDaoInterface
import com.example.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisilerDaoRepository(var application: Application) {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    var kisilerDaoInterface : KisilerDaoInterface

    init {
        kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
       kisilerDaoInterface.tumKisiler().enqueue(object : Callback<KisilerCevap> {
           override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
               val liste = response.body()!!.kisiler
               kisilerListesi.value = liste
           }

           override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}

       })
    }

    fun kisiAra(aramaKelimesi: String) {
        kisilerDaoInterface.kisiAra(aramaKelimesi).enqueue(object : Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body()!!.kisiler
                kisilerListesi.value = liste
            }

            override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}

        })

    }

    fun kisiKayit(kisiAdi: String, kisiTel: String) {
        kisilerDaoInterface.kisiEkle(kisiAdi, kisiTel).enqueue(object  : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {}

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}

        })

    }

    fun kisiGuncelle(kisi_id: Int, kisiAdi: String, kisiTel: String) {
        kisilerDaoInterface.kisiGuncelle(kisi_id,kisiAdi, kisiTel).enqueue(object  : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {}

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}

        })
    }

    fun kisiSil(kisi_id: Int) {
       kisilerDaoInterface.kisiSil(kisi_id).enqueue(object  : Callback<CRUDCevap>{
           override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
               tumKisileriAl()
           }

           override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}

       })
    }
}