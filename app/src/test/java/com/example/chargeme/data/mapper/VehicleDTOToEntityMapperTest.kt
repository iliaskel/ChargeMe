package com.example.chargeme.data.mapper

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.remotedatasource.response.Current
import com.example.chargeme.dummyCurrentList
import com.example.chargeme.dummyVehicleEntityList
import com.example.chargeme.emptyCurrentList
import com.example.chargeme.emptyVehiclesEntityList
import org.junit.Assert.assertEquals
import org.junit.Test

class VehicleDTOToEntityMapperTest {

    private val mapper: SourceToTargetModelMapper<Current, VehicleEntity> =
        VehicleDTOToEntityMapper()

    @Test
    fun mapSourceToTargetList_EmptyListInput_EmptyListOutput() {
        val mappedList = mapper.mapSourceToTargetList(emptyCurrentList)

        assertEquals(mappedList, emptyVehiclesEntityList)
    }

    @Test
    fun mapSourceToTargetList_HasInput_ReturnsCorrectOutput() {
        val mappedList = mapper.mapSourceToTargetList(dummyCurrentList)

        assertEquals(mappedList, dummyVehicleEntityList)
    }

    @Test
    fun mapSourceToTarget() {
        val dummyCurrent = dummyCurrentList[0]
        val dummyEntity = dummyVehicleEntityList[0]

        val mappedEntity = mapper.mapSourceToTarget(dummyCurrent)

        assertEquals(mappedEntity, dummyEntity)
    }
}