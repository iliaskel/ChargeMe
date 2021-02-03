package com.example.chargeme.data.remotedatasource.response

import com.google.gson.annotations.SerializedName

data class Current(
	@SerializedName("vehicleId") val id: String,
	@SerializedName("latitude") val latitude: Double,
	@SerializedName("longitude") val longitude: Double,
)