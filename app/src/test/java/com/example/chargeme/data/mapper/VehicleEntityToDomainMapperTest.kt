package com.example.chargeme.data.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.dummyVehicleEntityList
import com.example.chargeme.dummyVehiclesDomainList
import com.example.chargeme.emptyVehicleDomainList
import com.example.chargeme.emptyVehiclesEntityList
import org.junit.Assert.assertEquals
import org.junit.Test

class VehicleEntityToDomainMapperTest {

    private val mapper: SourceToTargetModelMapper<VehicleEntity, VehicleDomain> =
        VehicleEntityToDomainMapper()

    @Test
    fun mapSourceToTargetList_EmptyListInput_EmptyListOutput() {
        val mappedList = mapper.mapSourceToTargetList(emptyVehiclesEntityList)

        assertEquals(mappedList, emptyVehicleDomainList)
    }

    @Test
    fun mapSourceToTargetList_HasInput_ReturnsCorrectOutput() {
        val mappedList = mapper.mapSourceToTargetList(dummyVehicleEntityList)

        assertEquals(mappedList, dummyVehiclesDomainList)
    }

    @Test
    fun mapSourceToTarget() {
        val dummyEntity = dummyVehicleEntityList[0]
        val dummyDomain = dummyVehiclesDomainList[0]

        val mappedEntity = mapper.mapSourceToTarget(dummyEntity)

        assertEquals(mappedEntity, dummyDomain)
    }
}