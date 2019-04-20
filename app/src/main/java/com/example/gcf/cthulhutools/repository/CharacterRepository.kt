package com.example.gcf.cthulhutools.repository

import android.app.Application
import com.example.gcf.cthulhutools.database.CthulhuDatabase
import com.example.gcf.cthulhutools.model.Character

class CharacterRepository(application: Application) {
    private val db = CthulhuDatabase.getInstance(application).characterDao()
    fun getAllCharacterName(): List<String> =
            db.getAllCharactersName()

    fun add(character: Character) =
            db.insert(character.toEntity())

    fun update(character: Character) =
            db.update(character.toEntity())

    fun delete(name: String) {
        db.delete(name)
    }

    fun get(name: String) =
            db.getCharacter(name)
}