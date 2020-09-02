package com.project.twitter.repository.model.favourite

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UrlsItem(

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("expanded_url")
	val expandedUrl: String? = null,

	@field:SerializedName("display_url")
	val displayUrl: String? = null,

	@field:SerializedName("indices")
	val indices: List<Int?>? = null
)