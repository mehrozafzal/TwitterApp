package com.project.twitter.repository.model.retweet

import com.google.gson.annotations.SerializedName

data class UserMentionsItem(

	@field:SerializedName("screen_name")
	val screenName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_str")
	val idStr: String? = null,

	@field:SerializedName("indices")
	val indices: List<Int?>? = null
)