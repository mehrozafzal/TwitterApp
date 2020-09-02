package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName

data class Metadata(

	@field:SerializedName("iso_language_code")
	val isoLanguageCode: String? = null,

	@field:SerializedName("result_type")
	val resultType: String? = null
)