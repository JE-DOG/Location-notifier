package ru.je_dog.core.data.factory

import androidx.room.RoomDatabase

interface RoomFactory<DB: RoomDatabase> {

    fun create(): DB

}