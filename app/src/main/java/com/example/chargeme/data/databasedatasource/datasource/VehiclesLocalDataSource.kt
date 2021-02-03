package com.example.chargeme.data.databasedatasource.datasource

import com.example.chargeme.data.databasedatasource.VehiclesDatabase
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.data.mapper.VehicleEntityToDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface VehiclesLocalDataSource {
    suspend fun replaceVehiclesList(vehicleList: List<VehicleEntity>)
    fun getAllVehiclesFlow(): Flow<List<VehicleDomain>>
}

class VehiclesLocalDataSourceImpl @Inject constructor(
    vehiclesDatabase: VehiclesDatabase,
    private val mapper: VehicleEntityToDomainMapper
) : VehiclesLocalDataSource {

    private val vehiclesDao = vehiclesDatabase.vehiclesDao()

    override suspend fun replaceVehiclesList(vehicleList: List<VehicleEntity>) {
        vehiclesDao.replaceVehicleList(vehicleList)
    }

    override fun getAllVehiclesFlow(): Flow<List<VehicleDomain>> {
        return vehiclesDao.getAllVehiclesFlow().map {
            mapper.mapSourceToTargetList(it)
        }
    }
}