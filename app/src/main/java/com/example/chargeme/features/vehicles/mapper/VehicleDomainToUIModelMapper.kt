package com.example.chargeme.features.vehicles.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.features.vehicles.model.VehicleUI
import javax.inject.Inject

class VehicleDomainToUIModelMapper @Inject constructor() :
    SourceToTargetModelMapper<VehicleDomain, VehicleUI> {
    override fun mapSourceToTarget(sourceObject: VehicleDomain): VehicleUI {
        return VehicleUI(id = sourceObject.id, sourceObject.latitude, sourceObject.longitude)
    }

    override fun mapSourceToTargetList(sourceObjectList: List<VehicleDomain>): List<VehicleUI> {
        return sourceObjectList.map {
            mapSourceToTarget(it)
        }
    }
}