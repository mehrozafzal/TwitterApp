package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AdditionalMediaInfo(

	@field:SerializedName("monetizable")
	val monetizable: Boolean? = null
)