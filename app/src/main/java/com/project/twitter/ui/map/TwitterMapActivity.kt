package com.project.twitter.ui.map

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.project.twitter.R
import com.project.twitter.repository.model.detail.TweetDetail
import com.project.twitter.repository.model.tweet.StatusesItem
import com.project.twitter.ui.BaseActivity
import com.project.twitter.ui.adapter.CustomInfoWindowAdapter
import com.project.twitter.ui.detail.DetailActivity
import com.project.twitter.ui.search.SearchActivity
import com.project.twitter.utils.GPSTracker
import com.project.twitter.utils.ToastUtil
import com.project.twitter.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_twitter_map.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap


class TwitterMapActivity : BaseActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private val markerDataMap: LinkedHashMap<String, StatusesItem> = LinkedHashMap()
    private val markerList: LinkedHashMap<String, Marker> = LinkedHashMap()
    private var previousRadius = "0"
    private var tweetFound = false
    private lateinit var timerTask: TimerTask
    private var timer = Timer()
    private lateinit var gpsTracker: GPSTracker
    private lateinit var mapFragment: SupportMapFragment

    private val mapViewModel by lazy {
        getViewModel<MapViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_map)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        gpsTracker = GPSTracker(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.isMyLocationEnabled = true
        setMarkerWindowClickListener()
        setCurrentLocationMarker()
        observeTweetsResponse()
        pollForRecentTweets()
    }

    private fun setMarkerWindowClickListener() {
        mMap?.setOnInfoWindowClickListener {
            if (it.tag == "-1") {
                ToastUtil.showCustomToast(this@TwitterMapActivity, "This is just you")
            } else {
                if (markerDataMap.isNotEmpty()) {
                    if (markerDataMap.containsKey(it.tag)) {
                        val tweet = markerDataMap[it.tag]
                        val intent = Intent(this, DetailActivity::class.java)
                        val tweetDetail = TweetDetail(
                            "@".plus(tweet?.user?.screenName),
                            tweet?.text,
                            tweet?.createdAt,
                            tweet?.user?.profileImageUrl,
                            tweet?.idStr!!
                        )
                        intent.putExtra("TweetDetail", tweetDetail)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun loadTweets(lat: String, lng: String, distance: String) {
        mapViewModel.loadTweets(lat, lng, distance)
    }

    private fun addMarkerToMap(statusesItem: StatusesItem) {
        val lat: Double = statusesItem.geo!!.coordinates!![0] as Double
        val lng: Double = statusesItem.geo.coordinates!![1] as Double
        val location = LatLng(lat, lng)
        val marker = mMap!!.addMarker(
            MarkerOptions().position(location).title(statusesItem.place?.name)
                .snippet(statusesItem.text)
        )
        marker.tag = statusesItem.idStr
        markerList[marker.tag.toString()] = marker
    }


    private fun setCurrentLocationMarker() {
        val currentLocation =
            LatLng(gpsTracker.getCurrentLatitude(), gpsTracker.getCurrentLongitude())
        mMap!!.setInfoWindowAdapter(CustomInfoWindowAdapter(this));
        val marker = mMap!!.addMarker(
            MarkerOptions().position(currentLocation)
                .title("Your location")
                .snippet("You are here right now")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        marker.tag = "-1"
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 5.0f))

    }

    fun updateRadius(view: View) {
        if (activityTwitterMap_enterRadiusEdt.text.toString() != "") {
            if (previousRadius != activityTwitterMap_enterRadiusEdt.text.toString()) {
                previousRadius = activityTwitterMap_enterRadiusEdt.text.toString()
                ToastUtil.showCustomToast(this, "Radius has been updated")
                loadTweets(
                    gpsTracker.getCurrentLatitude().toString(),
                    gpsTracker.getCurrentLongitude().toString(),
                    activityTwitterMap_enterRadiusEdt.text.toString().plus("km")
                )
            }
        }
    }

    fun searchTweets(view: View) {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    private fun observeTweetsResponse() {
        mapViewModel.searchResponseLiveData.observe(this, Observer {
            if (it.statuses != null) {
                for (status in it.statuses) {
                    if (status?.geo != null) {
                        if (status.geo.coordinates != null) {
                            if (status.geo.coordinates.isNotEmpty()) {
                                if (!markerDataMap.containsKey(status.idStr!!)) {
                                    markerDataMap[status.idStr] = status
                                    //addMarkerToMap(status)
                                }
                            }
                        }
                    }
                }
                for (status in markerDataMap) {
                    addMarkerToMap(status.value)
                }
            }
        })
    }

    private fun removeLastElements() {
        val indexedList = ArrayList<Map.Entry<String, StatusesItem>>(markerDataMap.entries)
        for (i in 0..10) {
            val entry = indexedList[i]
            markerDataMap.remove(entry.key)
            val marker: Marker = markerList[entry.key] as Marker
            marker.remove()
        }
        println("MarkerSize:".plus(markerDataMap.size))

    }

    private fun pollForRecentTweets() {
        timerTask = object : TimerTask() {
            override fun run() {
                if (markerDataMap.size >= 100) {
                    removeLastElements()
                }
                if (previousRadius != "0")
                    loadTweets(
                        gpsTracker.getCurrentLatitude().toString(),
                        gpsTracker.getCurrentLongitude().toString(),
                        previousRadius.plus("km")
                    )
                else
                    loadTweets(
                        gpsTracker.getCurrentLatitude().toString(),
                        gpsTracker.getCurrentLongitude().toString(),
                        "5km"
                    )
            }
        }
        timer.schedule(timerTask, 2000, 20000);
    }

    override fun onResume() {
        mapFragment.onResume()
        super.onResume()
        if (mMap != null)
            pollForRecentTweets()
    }


    override fun onPause() {
        mapFragment.onPause()
        super.onPause()
        timerTask.cancel()
    }

}