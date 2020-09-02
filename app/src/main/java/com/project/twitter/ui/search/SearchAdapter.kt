package com.project.twitter.ui.search

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.twitter.R
import com.project.twitter.repository.model.search.SearchData


class SearchAdapter(
    val context: Context,
    val searchList: List<SearchData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_search_image -> {
                val inflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.item_search_image, parent, false);
                ImageViewHolder(view)
            }
            R.layout.item_search_video -> {
                val inflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.item_search_video, parent, false);
                VideoViewHolder(view)
            }
            R.layout.item_search -> {
                val inflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.item_search, parent, false);
                ViewHolder(view)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            searchList[position].mediaType?.contains("photo")!! -> R.layout.item_search_image
            searchList[position].mediaType?.contains("video")!! -> R.layout.item_search_video
            searchList[position].mediaType?.contains("")!! -> R.layout.item_search
            else -> R.layout.item_search
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_search_image -> (holder as ImageViewHolder).bind(
                position,
                searchList[position]
            )
            R.layout.item_search_video -> (holder as VideoViewHolder).bind(
                position,
                searchList[position]
            )

            R.layout.item_search -> (holder as ViewHolder).bind(
                position,
                searchList[position]
            )
        }
    }


    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, searchData: SearchData) {
            val avatar = itemView.findViewById<View>(R.id.item_search_image_avatar) as ImageView
            val tweetImage =
                itemView.findViewById<View>(R.id.item_search_image_tweetImage) as ImageView
            val handle = itemView.findViewById<View>(R.id.item_search_image_handle) as TextView
            val tweetText =
                itemView.findViewById<View>(R.id.item_search_image_tweetText) as TextView
            Glide.with(context)
                .load(searchData.userAvatar)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.default_user_image)
                        .error(R.drawable.default_user_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(avatar)

            Glide.with(context)
                .load(searchData.tweetUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(tweetImage)

            handle.text = searchData.twitterHandle
            tweetText.text = searchData.tweetText
        }
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, searchData: SearchData) {
            val avatar = itemView.findViewById<View>(R.id.item_search_video_avatar) as ImageView
            val handle = itemView.findViewById<View>(R.id.item_search_video_handle) as TextView
            val tweetText =
                itemView.findViewById<View>(R.id.item_search_video_tweetText) as TextView
            val videoView: VideoView =
                itemView.findViewById<View>(R.id.item_search_video_tweetVideo) as VideoView
            handle.text = searchData.twitterHandle
            tweetText.text = searchData.tweetText
            val uri: Uri = Uri.parse(searchData.tweetUrl)
            videoView.setVideoURI(uri)
            val mediaController = MediaController(context)
            mediaController.setAnchorView(videoView)
            videoView.requestFocus()

            videoView.setOnPreparedListener { mp ->
                val videoRatio: Float = (mp.videoWidth / mp.videoHeight).toFloat()
                val screenRatio: Float =
                    (videoView.width / videoView.height).toFloat()
                val scaleX = videoRatio / screenRatio
                if (scaleX >= 1f) {
                    videoView.scaleX = scaleX
                } else {
                    videoView.scaleY = 1f / scaleX
                }
                videoView.start()
            }

            Glide.with(context)
                .load(searchData.userAvatar)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(avatar)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, searchData: SearchData) {
            val avatar = itemView.findViewById<View>(R.id.item_search_avatar) as ImageView
            val handle = itemView.findViewById<View>(R.id.item_search_handle) as TextView
            val tweetText =
                itemView.findViewById<View>(R.id.item_search_tweetText) as TextView
            handle.text = searchData.twitterHandle
            tweetText.text = searchData.tweetText
            Glide.with(context)
                .load(searchData.userAvatar)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(avatar)
        }
    }
}