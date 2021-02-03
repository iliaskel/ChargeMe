package com.example.chargeme.usecases

import com.example.chargeme.features.vehicles.map.repository.VehiclesRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ReplaceVehiclesUCTest {
    @MockK
    private lateinit var vehiclesRepositoryMock: VehiclesRepository

    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun execute_VehiclesRepositoryCalled() = runBlockingTest {
        val replaceVehiclesUC = ReplaceVehiclesUC(vehiclesRepositoryMock)

        replaceVehiclesUC.execute()

        verify { runBlockingTest { vehiclesRepositoryMock.updateVehiclesList() } }
    }
}