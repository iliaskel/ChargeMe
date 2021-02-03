package com.example.chargeme.features.vehicles.map.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.chargeme.features.vehicles.map.mapper.VehicleDomainToUIModelMapper
import com.example.chargeme.usecases.GetVehiclesListFlowUC
import com.example.chargeme.usecases.ReplaceVehiclesUC
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VehiclesViewModelImplTest {

    @MockK
    private lateinit var replaceVehiclesUCMock: ReplaceVehiclesUC

    @MockK
    private lateinit var getVehiclesListFlowUCMock: GetVehiclesListFlowUC

    @MockK
    private lateinit var savedStateHandleMock: SavedStateHandle

    private val mapper: VehicleDomainToUIModelMapper = VehicleDomainToUIModelMapper()
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var vehiclesViewModel: VehiclesViewModel

    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        vehiclesViewModel = VehiclesViewModelImpl(
            replaceVehiclesUCMock,
            getVehiclesListFlowUCMock,
            mapper,
            savedStateHandleMock,
            testDispatcher
        )
    }

    @Test
    fun updateVehicles() = testDispatcher.runBlockingTest {
        vehiclesViewModel.updateVehicles()

        verify { runBlocking { replaceVehiclesUCMock.execute() } }
    }
}