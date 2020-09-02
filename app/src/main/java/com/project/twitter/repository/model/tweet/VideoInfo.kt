package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoInfo(

	@field:SerializedName("aspect_ratio")
	val aspectRatio: List<Int?>? = null,

	@field:SerializedName("duration_millis")
	val durationMillis: Int? = null,

	@field:SerializedName("variants")
	val variants: List<VariantsItem?>? = null
)