package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName

data class TwitterSearchResponse(

	@field:SerializedName("statuses")
	val statuses: List<StatusesItem?>? = null,

	@field:SerializedName("search_metadata")
	val searchMetadata: SearchMetadata? = null
)