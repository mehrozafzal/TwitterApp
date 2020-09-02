package com.project.twitter.repository.model.retweet

import com.google.gson.annotations.SerializedName

data class Place(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("place_type")
	val placeType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("contained_within")
	val containedWithin: List<Any?>? = null,

	@field:SerializedName("bounding_box")
	val boundingBox: BoundingBox? = null,

	@field:SerializedName("attributes")
	val attributes: Attributes? = null
)