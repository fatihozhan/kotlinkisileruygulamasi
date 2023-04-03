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
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisilerDaoRepository(var application: Application) {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    var refKisiler: DatabaseReference

    init {
        val db =
            FirebaseDatabase.getInstance("https://start-e1f22-default-rtdb.europe-west1.firebasedatabase.app")
        refKisiler = db.getReference("kisiler")
        kisilerListesi = MutableLiveData<List<Kisiler>>()
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun tumKisileriAl() {
        refKisiler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Kisiler>()
                for (d in snapshot.children){
                    val kisi = d.getValue(Kisiler::class.java)
                    if (kisi != null){
                        kisi.kisi_id = d.key
                        liste.add(kisi)
                    }
                }
                kisilerListesi.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    fun kisiAra(aramaKelimesi: String) {
        refKisiler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Kisiler>()
                for (d in snapshot.children){
                    val kisi = d.getValue(Kisiler::class.java)
                    if (kisi != null){
                        if (kisi.kisi_adi!!.lowercase().contains(aramaKelimesi.lowercase())){

                        kisi.kisi_id = d.key
                            liste.add(kisi)
                        }
                    }
                }
                kisilerListesi.value = liste
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    fun kisiKayit(kisiAdi: String, kisiTel: String) {
        val yeniKisi = Kisiler("", kisiAdi, kisiTel)
        refKisiler.push().setValue(yeniKisi)

    }

    fun kisiGuncelle(kisi_id: String, kisiAdi: String, kisiTel: String) {
    val bilgiler = HashMap<String,Any>()
        bilgiler["kisi_adi"]= kisiAdi
        bilgiler["kisi_tel"]= kisiTel
        refKisiler.child(kisi_id).updateChildren(bilgiler)
    }

    fun kisiSil(kisi_id: String) {
        refKisiler.child(kisi_id).removeValue()
    }
}