package com.project.twitter.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.twitter.R
import com.project.twitter.repository.model.search.SearchData
import com.project.twitter.ui.BaseActivity
import com.project.twitter.utils.ToastUtil
import com.project.twitter.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private var searchList: ArrayList<SearchData> = ArrayList<SearchData>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var progress: ContentLoadingProgressBar
    private var previousSearchText = ""
    private val searchViewModel by lazy {
        getViewModel<SearchViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        bindViews()
    }


    private fun bindViews() {
        progress =
            activitySearch_progress.findViewById<View>(R.id.exception_progressbar) as ContentLoadingProgressBar
        observeTweets()
    }

    fun searchTweets(view: View) {
        if (activitySearch_searchEdt.text.toString() != "" && activitySearch_searchEdt.text.toString() != previousSearchText) {
            previousSearchText = activitySearch_searchEdt.text.toString()
            toggleProgress(true)
            searchViewModel.loadTweets(previousSearchText)
        }
    }


    private fun observeTweets() {
        searchViewModel.searchResponseLiveData.observe(this, Observer {
            toggleProgress(false)
            if (it != null) {
                if (it.statuses != null) {
                    if (it.statuses.isNotEmpty()) {
                        activitySearch_noTweetTxt.visibility = View.GONE
                        searchList.clear()
                        for (tweet in it.statuses) {
                            val searchData = SearchData()
                            tweet?.user?.let { user ->
                                searchData.twitterHandle = user.screenName
                                searchData.userAvatar = user.profileImageUrl
                            }
                            searchData.tweetText = tweet?.text
                            searchData.tweetTimeStamp = tweet?.createdAt
                            if (tweet?.extendedEntities != null) {
                                if (tweet.extendedEntities.media != null) {
                                    val media = tweet.extendedEntities.media[0]
                                    if (media?.type == "video" || media?.type == "photo") {
                                        searchData.mediaType = media.type
                                        if (media.videoInfo != null) {
                                            if (media.videoInfo.variants != null) {
                                                if (media.videoInfo.variants.isNotEmpty())
                                                    searchData.tweetUrl =
                                                        media.videoInfo.variants[0]?.url
                                            }
                                        } else {
                                            searchData.tweetUrl = media.mediaUrl
                                        }
                                    }
                                }
                            }
                            searchList.add(searchData)

                        }
                        populateSearchList()
                    } else {
                        searchList.clear()
                        populateSearchList()
                        activitySearch_noTweetTxt.visibility = View.VISIBLE
                    }
                }
            } else {
                ToastUtil.showCustomToast(this, "Something went wrong")
            }
        })
    }

    private fun populateSearchList() {
        searchAdapter = SearchAdapter(this, searchList)
        activitySearch_searchRv.layoutManager = LinearLayoutManager(this)
        activitySearch_searchRv.adapter = searchAdapter

    }

    private fun toggleProgress(show: Boolean) {
        if (show)
            progress.visibility = View.VISIBLE
        else
            progress.visibility = View.GONE

    }


}