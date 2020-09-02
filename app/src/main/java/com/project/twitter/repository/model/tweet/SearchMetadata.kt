package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName

data class SearchMetadata(

	@field:SerializedName("completed_in")
	val completedIn: Any? = null,

	@field:SerializedName("max_id")
	val maxId: Long? = null,

	@field:SerializedName("max_id_str")
	val maxIdStr: String? = null,

	@field:SerializedName("next_results")
	val nextResults: String? = null,

	@field:SerializedName("query")
	val query: String? = null,

	@field:SerializedName("refresh_url")
	val refreshUrl: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("since_id")
	val sinceId: Int? = null,

	@field:SerializedName("since_id_str")
	val sinceIdStr: String? = null
)