package com.example.chargeme.usecases

import com.example.chargeme.features.vehicles.repository.VehiclesRepository
import javax.inject.Inject

class ReplaceVehiclesUC @Inject constructor(
    private val vehiclesRepository: VehiclesRepository
) {
    suspend fun execute() {
        vehiclesRepository.updateVehiclesList()
    }
}