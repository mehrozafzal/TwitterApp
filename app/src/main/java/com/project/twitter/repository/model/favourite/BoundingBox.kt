package com.project.twitter.repository.model.favourite

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BoundingBox(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("coordinates")
	val coordinates: List<List<List<Any?>?>?>? = null
)