package com.example.chargeme.data.remotedatasource.response

import com.google.gson.annotations.SerializedName

data class Stats(
	@SerializedName("open") val open: Int,
	@SerializedName("assigned") val assigned: Int,
	@SerializedName("resolved") val resolved: Int
)