package com.example.chargeme.usecases

import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.features.vehicles.repository.VehiclesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehiclesListFlowUC @Inject constructor(
    private val vehiclesRepository: VehiclesRepository
) {
    fun execute(): Flow<List<VehicleDomain>> {
        return vehiclesRepository.getAllVehiclesFlow()
    }
}