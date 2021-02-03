package com.example.chargeme

import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.data.remotedatasource.response.Current
import com.example.chargeme.features.vehicles.map.model.VehicleUI
import kotlinx.coroutines.flow.flow

val dummyVehiclesDomainList = listOf(
    VehicleDomain("1", 1.0, 1.0),
    VehicleDomain("2", 2.0, 2.0)
)

val dummyCurrentList = listOf(
    Current("1", 1.0, 1.0),
    Current("2", 2.0, 2.0)
)

val dummyVehicleEntityList = listOf(
    VehicleEntity("1", 1.0, 1.0),
    VehicleEntity("2", 2.0, 2.0)
)

val dummyVehicleUIList = listOf(
    VehicleUI("1", 1.0, 1.0),
    VehicleUI("2", 2.0, 2.0)
)

val emptyVehiclesEntityList = emptyList<VehicleEntity>()
val emptyVehiclesUIList = emptyList<VehicleUI>()
val emptyVehicleDomainList = emptyList<VehicleDomain>()
val emptyCurrentList = emptyList<Current>()

val emptyVehicleDomainFlowList = flow<List<VehicleDomain>> { emptyList<VehicleDomain>() }
val vehicleDomainFlowList = flow<List<VehicleDomain>> { dummyVehiclesDomainList }
