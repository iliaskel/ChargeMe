package com.example.chargeme.data.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.domainmodel.VehicleDomain
import javax.inject.Inject

class VehicleEntityToDomainMapper @Inject constructor() :
    SourceToTargetModelMapper<VehicleEntity, VehicleDomain> {
    override fun mapSourceToTarget(sourceObject: VehicleEntity): VehicleDomain {
        return VehicleDomain(sourceObject.id, sourceObject.latitude, sourceObject.longitude)
    }

    override fun mapSourceToTargetList(entityObjectList: List<VehicleEntity>): List<VehicleDomain> {
        return entityObjectList.map {
            mapSourceToTarget(it)
        }
    }
}