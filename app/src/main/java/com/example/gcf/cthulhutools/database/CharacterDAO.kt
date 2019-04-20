package com.example.gcf.cthulhutools.database

import android.arch.persistence.room.*

@Dao
interface CharacterDAO {
    @Query("select name from character")
    fun getAllCharactersName(): List<String>

    @Query("select * from character where name = :name")
    fun getCharacter(name: String): CharacterEntity

    @Insert
    fun insert(character: CharacterEntity)

    @Update
    fun update(character: CharacterEntity)

    @Query("DELETE FROM character WHERE name = :name")
    fun delete(name: String)
}