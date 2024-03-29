package com.example.kisileruygulamasi

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.ui.theme.KisilerUygulamasiTheme
import com.example.kisileruygulamasi.viewmodel.AnasayfaViewModel
import com.example.kisileruygulamasi.viewmodelfactory.AnasayfaViewModelFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KisilerUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Composable
fun Anasayfa(navController: NavController) {
    val aramaYapılıyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: AnasayfaViewModel = viewModel(
        factory = AnasayfaViewModelFactory(context.applicationContext as Application)
    )
    val kisilerListesi = viewModel.kisilerListesi.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.kisileriYukle()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapılıyorMu.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = { viewModel.ara(it); tf.value = it },
                            label = {
                                Text(text = "Ara")
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            )
                        )
                    } else {

                        Text(text = "Başlık")
                    }
                },
                actions = {
                    if (aramaYapılıyorMu.value) {
                        IconButton(onClick = {
                            aramaYapılıyorMu.value = false
                            tf.value = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription = "", tint = Color.White
                            )
                        }
                    } else {

                        IconButton(onClick = {
                            aramaYapılıyorMu.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arama_resim),
                                contentDescription = "", tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        content = { it ->
            it
            LazyColumn {
                items(
                    count = kisilerListesi.value!!.count(),
                    itemContent = {
                        val kisi = kisilerListesi.value!![it]
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.clickable {
                                val kisiJson =
                                    Gson().toJson(kisi);navController.navigate("kisi_detay/${kisiJson}")
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${kisi.kisi_adi} - ${kisi.kisi_tel}")

                                    IconButton(onClick = {
                                        viewModel.sil(kisi.kisi_id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.sil_resim),
                                            contentDescription = "",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("kisi_kayit")
            },
                backgroundColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )
        }
    )
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Anasayfa(navController = navController)
        }
        composable("kisi_kayit") {
            KisiKayit()
        }
        composable("kisi_detay/{kisi}", arguments = listOf(
            navArgument("kisi") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("kisi")!!
            val nesne = Gson().fromJson(json, Kisiler::class.java)
            KisiDetay(gelenKisi = nesne)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KisilerUygulamasiTheme {

    }
}