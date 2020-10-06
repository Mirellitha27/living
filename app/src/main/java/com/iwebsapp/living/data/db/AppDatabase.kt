package com.iwebsapp.living.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iwebsapp.living.data.db.entities.Services
import com.iwebsapp.living.data.db.entities.ServicesDao
import com.iwebsapp.living.data.db.entities.Token
import com.iwebsapp.living.data.db.entities.TokenDao

@Database(
    entities = [Token::class, Services::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getTokenDao(): TokenDao
    abstract fun getServicesDao(): ServicesDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}