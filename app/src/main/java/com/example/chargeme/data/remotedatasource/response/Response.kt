package com.example.chargeme.data.remotedatasource.response

import com.google.gson.annotations.SerializedName

data class Response (
	@SerializedName("data") val data : Data
)