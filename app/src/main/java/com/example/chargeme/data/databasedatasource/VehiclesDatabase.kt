package com.example.chargeme.data.databasedatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chargeme.data.databasedatasource.dao.VehicleDao
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity

@Database(
    entities = [VehicleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class VehiclesDatabase : RoomDatabase() {
    abstract fun vehiclesDao(): VehicleDao
}