package com.example.chargeme.features.vehicles.map.repository

import com.example.chargeme.data.databasedatasource.datasource.VehiclesLocalDataSource
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.data.mapper.VehicleDTOToEntityMapper
import com.example.chargeme.data.remotedatasource.datasource.VehiclesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface VehiclesRepository {
    suspend fun updateVehiclesList()
    fun getAllVehiclesFlow(): Flow<List<VehicleDomain>>
}

class VehiclesRepositoryImpl @Inject constructor(
    private val vehiclesLocalDataSource: VehiclesLocalDataSource,
    private val vehiclesRemoteDataSource: VehiclesRemoteDataSource,
    private val mapper: VehicleDTOToEntityMapper
) : VehiclesRepository {
    override suspend fun updateVehiclesList() {
        val remoteVehiclesResponse = vehiclesRemoteDataSource.fetchVehicles()
        val vehiclesEntityList = mapper.mapSourceToTargetList(remoteVehiclesResponse)
        vehiclesLocalDataSource.replaceVehiclesList(vehiclesEntityList)
    }

    override fun getAllVehiclesFlow(): Flow<List<VehicleDomain>> {
        return vehiclesLocalDataSource.getAllVehiclesFlow()
    }
}