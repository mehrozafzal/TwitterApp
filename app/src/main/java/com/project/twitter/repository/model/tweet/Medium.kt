package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Medium(

	@field:SerializedName("w")
	val W: Int? = null,

	@field:SerializedName("h")
	val H: Int? = null,

	@field:SerializedName("resize")
	val resize: String? = null
)