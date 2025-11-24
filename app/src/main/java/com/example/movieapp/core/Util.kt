package com.example.movieapp.core

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.widget.ImageView
import com.bumptech.glide.Glide

object Util {

    fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url).into(this)
    }
    fun isGpsEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
    fun openGpsSettings(context: Context) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }
    fun openAppPermissionSettings(context: Context) {
        val packageName = context.packageName
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        context.startActivity(intent)
    }
}