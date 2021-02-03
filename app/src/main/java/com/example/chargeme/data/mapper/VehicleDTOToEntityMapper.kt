package com.example.chargeme.data.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.remotedatasource.response.Current
import javax.inject.Inject

class VehicleDTOToEntityMapper @Inject constructor() :
    SourceToTargetModelMapper<Current, VehicleEntity> {
    override fun mapSourceToTarget(sourceObject: Current): VehicleEntity {
        return VehicleEntity(
            id = sourceObject.id,
            latitude = sourceObject.latitude,
            longitude = sourceObject.longitude
        )
    }

    override fun mapSourceToTargetList(sourceObjectList: List<Current>): List<VehicleEntity> {
        return sourceObjectList.map {
            mapSourceToTarget(it)
        }
    }
}