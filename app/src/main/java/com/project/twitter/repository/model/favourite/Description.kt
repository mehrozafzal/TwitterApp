package com.project.twitter.repository.model.favourite

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Description(

	@field:SerializedName("urls")
	val urls: List<Any?>? = null
)