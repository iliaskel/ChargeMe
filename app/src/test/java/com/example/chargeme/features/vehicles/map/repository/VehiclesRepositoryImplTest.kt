package com.example.chargeme.features.vehicles.map.repository

import com.example.chargeme.data.databasedatasource.datasource.VehiclesLocalDataSource
import com.example.chargeme.data.mapper.VehicleDTOToEntityMapper
import com.example.chargeme.data.remotedatasource.datasource.VehiclesRemoteDataSource
import com.example.chargeme.dummyCurrentList
import com.example.chargeme.emptyCurrentList
import com.example.chargeme.emptyVehicleDomainFlowList
import com.example.chargeme.vehicleDomainFlowList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VehiclesRepositoryImplTest {
    @MockK
    private lateinit var vehiclesLocalDataSourceMock: VehiclesLocalDataSource

    @MockK
    private lateinit var vehiclesRemoteDataSourceMock: VehiclesRemoteDataSource

    private val mapper = VehicleDTOToEntityMapper()
    private lateinit var vehiclesRepository: VehiclesRepository

    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        vehiclesRepository =
            VehiclesRepositoryImpl(
                vehiclesLocalDataSourceMock,
                vehiclesRemoteDataSourceMock,
                mapper
            )
    }

    @Test
    fun updateVehiclesList_RemoteReturnsEmptyList_LocalReplacesWithEmptyList() = runBlockingTest {
        every { runBlocking { vehiclesRemoteDataSourceMock.fetchVehicles() } } returns emptyCurrentList
        val emptyVehiclesEntityList = mapper.mapSourceToTargetList(emptyCurrentList)

        vehiclesRepository.updateVehiclesList()

        verify { runBlocking { vehiclesRemoteDataSourceMock.fetchVehicles() } }
        verify {
            runBlocking {
                vehiclesLocalDataSourceMock.replaceVehiclesList(
                    emptyVehiclesEntityList
                )
            }
        }
    }

    @Test
    fun updateVehiclesList_RemoteReturnsCurrentList_LocalReplacesWithCorrectEntityList() =
        runBlockingTest {
            every { runBlocking { vehiclesRemoteDataSourceMock.fetchVehicles() } } returns dummyCurrentList
            val vehiclesEntityList = mapper.mapSourceToTargetList(dummyCurrentList)

            vehiclesRepository.updateVehiclesList()

            verify { runBlocking { vehiclesRemoteDataSourceMock.fetchVehicles() } }
            verify {
                runBlocking {
                    vehiclesLocalDataSourceMock.replaceVehiclesList(
                        vehiclesEntityList
                    )
                }
            }
        }

    @Test
    fun getAllVehiclesListFlow_localReturnsEmptyList_RepositoryReturnsEmptyList() {
        every { vehiclesLocalDataSourceMock.getAllVehiclesFlow() } returns emptyVehicleDomainFlowList

        val returnedListFlow = vehiclesRepository.getAllVehiclesFlow()

        assertEquals(returnedListFlow, emptyVehicleDomainFlowList)
    }

    @Test
    fun getAllVehiclesListFlow_localReturnsFlowList_RepositoryReturnsSameFlowList() {
        every { vehiclesLocalDataSourceMock.getAllVehiclesFlow() } returns vehicleDomainFlowList

        val returnedListFlow = vehiclesRepository.getAllVehiclesFlow()

        assertEquals(returnedListFlow, vehicleDomainFlowList)
    }
}