package com.example.gcf.cthulhutools.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CthulhuDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDAO

    companion object {
        @Volatile
        private var instance: CthulhuDatabase? = null

        fun getInstance(context: Context): CthulhuDatabase =
                instance ?: synchronized(this) {
                    instance ?: Room.databaseBuilder(context,
                            CthulhuDatabase::class.java, "cthulhu.db").allowMainThreadQueries().build()
                            .apply { instance = this }
                }
    }
}