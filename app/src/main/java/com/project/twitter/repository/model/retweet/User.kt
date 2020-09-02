package com.project.twitter.repository.model.retweet

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("id_str")
	val idStr: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("screen_name")
	val screenName: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("url")
	val url: Any? = null,

	@field:SerializedName("entities")
	val entities: Entities? = null,

	@field:SerializedName("protected")
	val jsonMemberProtected: Boolean? = null,

	@field:SerializedName("followers_count")
	val followersCount: Int? = null,

	@field:SerializedName("friends_count")
	val friendsCount: Int? = null,

	@field:SerializedName("listed_count")
	val listedCount: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("favourites_count")
	val favouritesCount: Int? = null,

	@field:SerializedName("utc_offset")
	val utcOffset: Any? = null,

	@field:SerializedName("time_zone")
	val timeZone: Any? = null,

	@field:SerializedName("geo_enabled")
	val geoEnabled: Boolean? = null,

	@field:SerializedName("verified")
	val verified: Boolean? = null,

	@field:SerializedName("statuses_count")
	val statusesCount: Int? = null,

	@field:SerializedName("lang")
	val lang: Any? = null,

	@field:SerializedName("contributors_enabled")
	val contributorsEnabled: Boolean? = null,

	@field:SerializedName("is_translator")
	val isTranslator: Boolean? = null,

	@field:SerializedName("is_translation_enabled")
	val isTranslationEnabled: Boolean? = null,

	@field:SerializedName("profile_background_color")
	val profileBackgroundColor: String? = null,

	@field:SerializedName("profile_background_image_url")
	val profileBackgroundImageUrl: String? = null,

	@field:SerializedName("profile_background_image_url_https")
	val profileBackgroundImageUrlHttps: String? = null,

	@field:SerializedName("profile_background_tile")
	val profileBackgroundTile: Boolean? = null,

	@field:SerializedName("profile_image_url")
	val profileImageUrl: String? = null,

	@field:SerializedName("profile_image_url_https")
	val profileImageUrlHttps: String? = null,

	@field:SerializedName("profile_banner_url")
	val profileBannerUrl: String? = null,

	@field:SerializedName("profile_link_color")
	val profileLinkColor: String? = null,

	@field:SerializedName("profile_sidebar_border_color")
	val profileSidebarBorderColor: String? = null,

	@field:SerializedName("profile_sidebar_fill_color")
	val profileSidebarFillColor: String? = null,

	@field:SerializedName("profile_text_color")
	val profileTextColor: String? = null,

	@field:SerializedName("profile_use_background_image")
	val profileUseBackgroundImage: Boolean? = null,

	@field:SerializedName("has_extended_profile")
	val hasExtendedProfile: Boolean? = null,

	@field:SerializedName("default_profile")
	val defaultProfile: Boolean? = null,

	@field:SerializedName("default_profile_image")
	val defaultProfileImage: Boolean? = null,

	@field:SerializedName("following")
	val following: Boolean? = null,

	@field:SerializedName("follow_request_sent")
	val followRequestSent: Boolean? = null,

	@field:SerializedName("notifications")
	val notifications: Boolean? = null,

	@field:SerializedName("translator_type")
	val translatorType: String? = null
)