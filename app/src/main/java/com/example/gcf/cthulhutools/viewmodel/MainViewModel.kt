package com.example.gcf.cthulhutools.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.gcf.cthulhutools.repository.CharacterRepository
import com.example.gcf.cthulhutools.model.Character

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = CharacterRepository(application)
    val characterNameList = MutableLiveData<List<String>>()
    val character = MutableLiveData<Character>()

    fun getAllCharacterName() : LiveData<List<String>> {
        loadAllCharacter()
        return characterNameList
    }

    fun loadAllCharacter() {
        characterNameList.value = repo.getAllCharacterName()
    }

    fun addCharacter(character: Character) {
        repo.add(character)
    }

    fun deleteCharacter(name: String) {
        repo.delete(name)
    }

    fun getCharacter(name: String): LiveData<Character> {
        character.value = Character.fromEntity(repo.get(name))
        return character
    }

    fun updateCharacter(character: Character) {
        repo.update(character)
    }
}