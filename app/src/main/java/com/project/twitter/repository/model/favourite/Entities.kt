package com.project.twitter.repository.model.favourite

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Entities(

	@field:SerializedName("hashtags")
	val hashtags: List<Any?>? = null,

	@field:SerializedName("symbols")
	val symbols: List<Any?>? = null,

	@field:SerializedName("user_mentions")
	val userMentions: List<Any?>? = null,

	@field:SerializedName("urls")
	val urls: List<UrlsItem?>? = null
)