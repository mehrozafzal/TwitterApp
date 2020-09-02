package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sizes(

	@field:SerializedName("thumb")
	val thumb: Thumb? = null,

	@field:SerializedName("medium")
	val medium: Medium? = null,

	@field:SerializedName("small")
	val small: Small? = null,

	@field:SerializedName("large")
	val large: Large? = null
)