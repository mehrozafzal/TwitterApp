package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VariantsItem(

	@field:SerializedName("bitrate")
	val bitrate: Int? = null,

	@field:SerializedName("content_type")
	val contentType: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)