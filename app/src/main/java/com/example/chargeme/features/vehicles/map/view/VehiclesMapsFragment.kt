package com.example.chargeme.features.vehicles.map.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chargeme.R
import com.example.chargeme.features.vehicles.map.model.VehicleUI
import com.example.chargeme.features.vehicles.map.viewmodel.VehiclesViewModelImpl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehiclesMapsFragment : Fragment(R.layout.fragment_maps) {

    private val vehiclesViewModel: VehiclesViewModelImpl by viewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        vehiclesViewModel.vehiclesUILiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) updateMap(it, googleMap)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vehiclesViewModel.updateVehicles()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun updateMap(vehiclesList: List<VehicleUI>, googleMap: GoogleMap) {
        putMarkers(vehiclesList, googleMap)
        moveCamera(vehiclesList, googleMap)
    }

    private fun moveCamera(
        vehiclesList: List<VehicleUI>,
        googleMap: GoogleMap
    ) {
        val firstLatLng = LatLng(vehiclesList[0].latitude, vehiclesList[0].longitude)
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(firstLatLng, 10f)
        )
    }

    private fun putMarkers(
        vehiclesList: List<VehicleUI>,
        googleMap: GoogleMap
    ) {
        vehiclesList.forEach { vehicleUI ->
            val latLng = LatLng(vehicleUI.latitude, vehicleUI.longitude)
            googleMap.addMarker(MarkerOptions().position(latLng).title(vehicleUI.id))
        }
    }
}