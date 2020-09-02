package com.project.twitter.ui.detail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.project.twitter.R
import com.project.twitter.repository.model.detail.TweetDetail
import com.project.twitter.ui.BaseActivity
import com.project.twitter.utils.ToastUtil
import com.project.twitter.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    private lateinit var tweetDetail: TweetDetail
    private lateinit var progress: ContentLoadingProgressBar

    private val detailViewModel by lazy {
        getViewModel<DetailViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindViews()
    }


    private fun bindViews() {
        progress =
            activityDetail_progress.findViewById<View>(R.id.exception_progressbar) as ContentLoadingProgressBar
        if (intent.extras != null) {
            tweetDetail = intent.getParcelableExtra("TweetDetail") as TweetDetail
            Glide.with(this)
                .load(tweetDetail.userAvatar)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.default_user_image)
                        .error(R.drawable.default_user_image)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(activityDetail_avatar)

            activityDetail_tweetTimestamp.text = tweetDetail.tweetTimeStamp
            activityDetail_handle.text = tweetDetail.twitterHandle
            activityDetail_tweetText.text = tweetDetail.tweetText
        }

        observeRetweetResponse()
        observeFavouriteResponse()
    }

    private fun observeRetweetResponse() {
        detailViewModel.retweetResponseLiveData.observe(this, Observer {
            toggleProgress(false)
            if (it != null) {
                ToastUtil.showCustomToast(this, "Tweet successfully retweeted")
            } else {
                ToastUtil.showCustomToast(this, "Something went wrong")
            }
        })

        detailViewModel.retweetResponseErrorLiveData.observe(this, Observer {
            toggleProgress(false)
            if (it != null) {
                ToastUtil.showCustomToast(this, it)
            } else {
                ToastUtil.showCustomToast(this, "Something went wrong")
            }
        })
    }

    private fun observeFavouriteResponse() {
        detailViewModel.favouriteResponseLiveData.observe(this, Observer {
            toggleProgress(false)
            if (it != null) {
                ToastUtil.showCustomToast(this, "Tweet successfully liked")
            } else {
                ToastUtil.showCustomToast(this, "Something went wrong")
            }
        })

        detailViewModel.favouriteResponseErrorLiveData.observe(this, Observer {
            toggleProgress(false)
            if (it != null) {
                ToastUtil.showCustomToast(this, it)
            } else {
                ToastUtil.showCustomToast(this, "Something went wrong")
            }
        })
    }

    fun likeTweet(view: View) {
        toggleProgress(true)
        detailViewModel.favouriteResponse(tweetDetail.tweetID)
    }

    fun retweet(view: View) {
        toggleProgress(true)
        detailViewModel.retweetResponse(tweetDetail.tweetID)
    }

    private fun toggleProgress(show: Boolean) {
        if (show)
            progress.visibility = View.VISIBLE
        else
            progress.visibility = View.GONE

    }

}