package com.example.gcf.cthulhutools.model

import com.example.gcf.cthulhutools.database.CharacterEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Character(val name: String, val job: String, val age: Int, val sex: String, val status: BasicStatus, val skills: Map<String, Int>, val background: String) {

    companion object {
        fun fromEntity(entity: CharacterEntity): Character {
            val gson = Gson()
            val status = gson.fromJson(entity.status, BasicStatus::class.java)
            val mapType = object : TypeToken<Map<String, Int>>(){ }.type
            val skills: Map<String, Int> = gson.fromJson(entity.skills, mapType)
            return Character(entity.name, entity.job, entity.age, entity.sex, status, skills, entity.background)
        }
    }

    fun toEntity(): CharacterEntity {
        val gson = Gson()
        return CharacterEntity(name, job, age, sex, gson.toJson(status), gson.toJson(skills), background)
    }
}