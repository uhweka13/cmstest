package com.beans.cmstest.data.localdb

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    private var instance: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "bean_database"
            ).build().also { instance = it }
        }
    }
}