package com.example.kisileruygulamasi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kisileruygulamasi.entity.Kisiler

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun kisilerDao(): KisierDao

    companion object {
        var instance: Veritabani? = null

        fun veritabaninaErisim(context: Context): Veritabani? {
            if (instance == null) {
                synchronized(Veritabani::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Veritabani::class.java,
                        "kisiler.sqlite"
                    ).createFromAsset("kisiler.sqlite").build()
                }
            }
            return instance

        }
    }


}