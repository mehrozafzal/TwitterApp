package com.project.twitter.repository.model.tweet

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaItem(

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("id_str")
	val idStr: String? = null,

	@field:SerializedName("indices")
	val indices: List<Int?>? = null,

	@field:SerializedName("media_url")
	val mediaUrl: String? = null,

	@field:SerializedName("media_url_https")
	val mediaUrlHttps: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("display_url")
	val displayUrl: String? = null,

	@field:SerializedName("expanded_url")
	val expandedUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("sizes")
	val sizes: Sizes? = null,

	@field:SerializedName("video_info")
	val videoInfo: VideoInfo? = null,

	@field:SerializedName("additional_media_info")
	val additionalMediaInfo: AdditionalMediaInfo? = null
)