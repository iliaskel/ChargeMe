package com.example.chargeme.features.vehicles.map.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.dummyVehicleUIList
import com.example.chargeme.dummyVehiclesDomainList
import com.example.chargeme.emptyVehicleDomainList
import com.example.chargeme.emptyVehiclesUIList
import com.example.chargeme.features.vehicles.map.model.VehicleUI
import org.junit.Assert.assertEquals
import org.junit.Test

class VehicleDomainToUIModelMapperTest {
    private val mapper: SourceToTargetModelMapper<VehicleDomain, VehicleUI> =
        VehicleDomainToUIModelMapper()

    @Test
    fun mapSourceToTargetList_EmptyListInput_EmptyListOutput() {
        val mappedList = mapper.mapSourceToTargetList(emptyVehicleDomainList)

        assertEquals(mappedList, emptyVehiclesUIList)
    }

    @Test
    fun mapSourceToTargetList_HasInput_ReturnsCorrectOutput() {
        val mappedList = mapper.mapSourceToTargetList(dummyVehiclesDomainList)

        assertEquals(mappedList, dummyVehicleUIList)
    }

    @Test
    fun mapSourceToTarget() {
        val dummyUI = dummyVehicleUIList[0]
        val dummyDomain = dummyVehiclesDomainList[0]

        val mappedEntity = mapper.mapSourceToTarget(dummyDomain)

        assertEquals(mappedEntity, dummyUI)
    }
}