package com.project.twitter.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import com.project.twitter.utils.GPSTracker
import com.project.twitter.utils.ToastUtil.showCustomToast
import java.util.*

open class GPSTracker(private val mContext: Context) : Service(), LocationListener {
    // flag for GPS status
    private var isGPSEnabled = false

    // flag for network status
    var isNetworkEnabled = false

    // flag for GPS status
    private var canGetLocation = false
    private var location: Location? = null
    private var latitude = 0.0
    private var longitude = 0.0

    // Declaring a Location Manager
    private lateinit var locationManager: LocationManager

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Location? {
        try {
            locationManager = mContext.getSystemService(LOCATION_SERVICE) as LocationManager
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGPSEnabled && !isNetworkEnabled) {
                showCustomToast(mContext, "No GPS or Network provider enabled")
            } else {
                canGetLocation = true
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    Log.d("Network", "Network")
                    location =
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                        )
                        Log.d("GPS Enabled", "GPS Enabled")
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("GPSTracker", e.message!!)
        }
        return location
    }

    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app.
     */
    fun stopUsingGPS() {
        locationManager.removeUpdates(this@GPSTracker)
    }

    /**
     * Function to get latitude
     */
    fun getCurrentLatitude(): Double {
        if (location != null) {
            latitude = location!!.latitude
        }
        // return latitude
        return latitude
    }

    /**
     * Function to get longitude
     */
    fun getCurrentLongitude(): Double {
        if (location != null) {
            longitude = location!!.longitude
        }

        // return longitude
        return longitude
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return canGetLocation
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        // Setting DialogHelp Title
        alertDialog.setTitle("GPS is settings")

        // Setting DialogHelp Message
        alertDialog
            .setMessage("GPS is not enabled. Do you want to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton(
            "Settings"
        ) { dialog, which ->
            val intent = Intent(
                Settings.ACTION_LOCATION_SOURCE_SETTINGS
            )
            mContext.startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }

    override fun onLocationChanged(location: Location) {
        var bestAccuracy = -1f
        if (location.accuracy != 0.0f && location.accuracy < bestAccuracy || bestAccuracy == -1f) {
            locationManager.removeUpdates(this)
        }
        bestAccuracy = location.accuracy
    }

    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    val accuracy: Float
        get() = location!!.accuracy

    companion object {
        // The minimum distance to change Updates in meters
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters

        // The minimum time between updates in milliseconds
        private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1 // 1 minute
            .toLong()
    }

    init {
        getCurrentLocation()
    }
}