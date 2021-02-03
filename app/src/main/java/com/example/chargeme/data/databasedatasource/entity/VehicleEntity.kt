package com.example.chargeme.data.databasedatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_table")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val latitude: Double,
    val longitude: Double
)