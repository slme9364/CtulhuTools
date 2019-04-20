package com.example.gcf.cthulhutools.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
        @PrimaryKey
        val name: String,

        val job: String,
        val age: Int,
        val sex: String,
        val status: String,
        val skills: String,
        val background: String
)