package com.example.chargeme.data.databasedatasource.datasource

import com.example.chargeme.data.databasedatasource.VehiclesDatabase
import com.example.chargeme.data.databasedatasource.dao.VehicleDao
import com.example.chargeme.data.mapper.VehicleEntityToDomainMapper
import com.example.chargeme.dummyVehicleEntityList
import com.example.chargeme.emptyVehiclesEntityList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VehiclesLocalDataSourceImplTest {
    @MockK
    private lateinit var vehiclesDatabaseMock: VehiclesDatabase

    @MockK
    private lateinit var vehiclesDaoMock: VehicleDao

    private val mapper = VehicleEntityToDomainMapper()
    private lateinit var vehiclesLocalDataSource: VehiclesLocalDataSource

    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        every { vehiclesDatabaseMock.vehiclesDao() } returns vehiclesDaoMock
        vehiclesLocalDataSource = VehiclesLocalDataSourceImpl(vehiclesDatabaseMock, mapper)
    }

    @Test
    fun replaceVehiclesList_ReceivesEmptyList_ReplacesWithEmptyList() = runBlockingTest {
        vehiclesLocalDataSource.replaceVehiclesList(emptyVehiclesEntityList)

        verify { runBlocking { vehiclesDaoMock.replaceVehicleList(emptyVehiclesEntityList) } }
    }

    @Test
    fun replaceVehiclesList_ReceivesList_ReplacesWithSameList() = runBlockingTest {
        vehiclesLocalDataSource.replaceVehiclesList(dummyVehicleEntityList)

        verify { runBlocking { vehiclesDaoMock.replaceVehicleList(dummyVehicleEntityList) } }
    }
}