package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExtendedEntity(

	@field:SerializedName("media")
	val media: List<MediaItem?>? = null
)