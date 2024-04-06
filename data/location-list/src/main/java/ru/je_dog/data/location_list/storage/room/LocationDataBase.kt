package ru.je_dog.data.location_list.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.je_dog.core.data.model.GeoPointEntity

@Database(
    entities = [
        GeoPointEntity::class
    ],
    version = 1
)
abstract class LocationDataBase: RoomDatabase() {

    abstract fun locationDao(): LocationDao

}