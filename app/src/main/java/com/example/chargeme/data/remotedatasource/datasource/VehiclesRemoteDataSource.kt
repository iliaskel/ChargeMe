package com.example.chargeme.data.remotedatasource.datasource

import com.example.chargeme.data.remotedatasource.api.VehiclesApi
import com.example.chargeme.data.remotedatasource.response.Current
import javax.inject.Inject

interface VehiclesRemoteDataSource {
    suspend fun fetchVehicles(): List<Current>
}

class VehiclesRemoteDataSourceImpl @Inject constructor(
    private val vehiclesApi: VehiclesApi
) : VehiclesRemoteDataSource {
    override suspend fun fetchVehicles(): List<Current> {
        return try {
            vehiclesApi.getData().data.current
        } catch (e: Exception) {
            emptyList()
        }
    }
}