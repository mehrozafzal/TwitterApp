package com.project.twitter.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker
import com.project.twitter.R
import com.project.twitter.ui.detail.DetailActivity

class CustomInfoWindowAdapter(private val mContext: Context) : InfoWindowAdapter {
    private val mWindow: View =
        LayoutInflater.from(mContext).inflate(R.layout.custom_info_window_layout, null)

    private fun rendowWindowText(marker: Marker, view: View) {
        val title = marker.title
        val tvTitle = view.findViewById<View>(R.id.customInfoWindow_handle) as TextView
        if (title != "") {
            tvTitle.text = title
        }
        val snippet = marker.snippet
        val tvSnippet = view.findViewById<View>(R.id.customInfoWindow_date) as TextView
        if (snippet != "") {
            tvSnippet.text = snippet
        }

     /*   view.findViewById<View>(R.id.customInfoWindow_detailBtn).setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            mContext.startActivity(intent)
        }*/
    }

    override fun getInfoWindow(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoContents(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

}