package com.project.twitter.repository.model.retweet

import com.google.gson.annotations.SerializedName

data class Coordinates(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("coordinates")
	val coordinates: List<Any?>? = null
)