package com.example.chargeme.data.remotedatasource.response

import com.google.gson.annotations.SerializedName

data class Data(
	@SerializedName("current") val current: List<Current>,
	@SerializedName("stats") val stats: Stats
)