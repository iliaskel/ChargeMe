package com.example.chargeme.data.remotedatasource.datasource

import com.example.chargeme.data.remotedatasource.api.VehiclesApi
import com.example.chargeme.data.remotedatasource.response.Current
import com.example.chargeme.dummyCurrentList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VehiclesRemoteDataSourceImplTest {
    @MockK
    private lateinit var vehiclesApiMock: VehiclesApi

    private lateinit var vehiclesRemoteDataSource: VehiclesRemoteDataSource

    @Before
    fun beforeEach() {
        MockKAnnotations.init(this, relaxed = true)
        vehiclesRemoteDataSource = VehiclesRemoteDataSourceImpl(vehiclesApiMock)
    }

    @Test
    fun fetchVehicles_NetworkException_ReturnsEmptyList() = runBlockingTest {
        every { runBlocking { vehiclesApiMock.getData() } } throws Exception()

        val remoteResponse = vehiclesRemoteDataSource.fetchVehicles()

        assertEquals(remoteResponse, emptyList<Current>())
    }

    @Test
    fun fetchVehicles_ApiReturnsNoData_RemoteReturnsEmptyList() = runBlockingTest {
        every { runBlocking { vehiclesApiMock.getData().data.current } } returns emptyList()

        val remoteResponse = vehiclesRemoteDataSource.fetchVehicles()

        assertEquals(remoteResponse, emptyList<Current>())
    }

    @Test
    fun fetchVehicles_ApiReturnsData_RemoteReturnsSameData() = runBlockingTest {
        every { runBlocking { vehiclesApiMock.getData().data.current } } returns dummyCurrentList

        val remoteResponse = vehiclesRemoteDataSource.fetchVehicles()

        assertEquals(remoteResponse, dummyCurrentList)
    }
}