package com.example.chargeme.features.vehicles.viewmodel

import androidx.lifecycle.*
import com.example.chargeme.core.di.IoDispatcher
import com.example.chargeme.features.vehicles.mapper.VehicleDomainToUIModelMapper
import com.example.chargeme.features.vehicles.model.VehicleUI
import com.example.chargeme.usecases.GetVehiclesListFlowUC
import com.example.chargeme.usecases.ReplaceVehiclesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

interface VehiclesViewModel {
    val vehiclesUILiveData: LiveData<List<VehicleUI>>
    fun updateVehicles()
}

@HiltViewModel
class VehiclesViewModelImpl @Inject constructor(
    private val replaceVehiclesUC: ReplaceVehiclesUC,
    private val getVehiclesListFlowUC: GetVehiclesListFlowUC,
    private val mapper: VehicleDomainToUIModelMapper,
    private val savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(), VehiclesViewModel {

    override val vehiclesUILiveData: LiveData<List<VehicleUI>> = observeVehicleUiLiveDataLst()

    override fun updateVehicles() {
        viewModelScope.launch(dispatcher) {
            replaceVehiclesUC.execute()
        }
    }

    private fun observeVehicleUiLiveDataLst(): LiveData<List<VehicleUI>> {
        return getVehiclesListFlowUC.execute().map {
            mapper.mapSourceToTargetList(it)
        }.asLiveData()
    }
}