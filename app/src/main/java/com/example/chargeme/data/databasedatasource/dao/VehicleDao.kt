package com.example.chargeme.data.databasedatasource.dao

import androidx.room.*
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicleList(vehicleList: List<VehicleEntity>)

    @Query("DELETE FROM vehicle_table")
    suspend fun deleteAllVehicles()

    @Transaction
    suspend fun replaceVehicleList(vehicleList: List<VehicleEntity>) {
        deleteAllVehicles()
        insertVehicleList(vehicleList)
    }

    @Query("SELECT * FROM vehicle_table")
    fun getAllVehicles(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle_table")
    fun getAllVehiclesFlow(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicle_table WHERE :id = id")
    fun getVehicleByVehicleIdFlow(id: String): Flow<VehicleEntity>
}