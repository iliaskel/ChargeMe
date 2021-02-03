package com.example.chargeme.usecases

import com.example.chargeme.emptyVehicleDomainFlowList
import com.example.chargeme.features.vehicles.repository.VehiclesRepository
import com.example.chargeme.vehicleDomainFlowList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetVehiclesListFlowUCTest {
    @MockK
    private lateinit var vehiclesRepositoryMock: VehiclesRepository

    private lateinit var getVehiclesListFlowUC: GetVehiclesListFlowUC


    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        getVehiclesListFlowUC = GetVehiclesListFlowUC(vehiclesRepositoryMock)
    }

    @Test
    fun execute_ReceivesEmptyList_ReturnsEmptyList() {
        every { vehiclesRepositoryMock.getAllVehiclesFlow() } returns emptyVehicleDomainFlowList

        val returnedVehiclesListFlow = getVehiclesListFlowUC.execute()

        assertEquals(returnedVehiclesListFlow, emptyVehicleDomainFlowList)
    }

    @Test
    fun execute_ReceivesList_ReturnsSameList() {
        every { vehiclesRepositoryMock.getAllVehiclesFlow() } returns vehicleDomainFlowList

        val returnedVehiclesListFlow = getVehiclesListFlowUC.execute()

        assertEquals(returnedVehiclesListFlow, vehicleDomainFlowList)
    }
}