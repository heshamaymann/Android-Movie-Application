package com.example.movieapp.features.fragment.location

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.core.Util
import com.example.movieapp.databinding.FragmentLocBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class LocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapFragment: SupportMapFragment
    private var binding_: FragmentLocBinding? = null
    private lateinit var googleMap: GoogleMap
    private val binding get() = binding_!!
    private val viewModel: LocationViewModel by viewModels()
    private val args by navArgs<LocationFragmentArgs>()

    private lateinit var fusedLocation: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentLocBinding.inflate(inflater, container, false)
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) {
            // Permission is granted. Continue the action or workflow in your
            // app.
            mapFragment.getMapAsync(this)
        } else {
            //Not Granted
            Toast.makeText(requireContext(), "not granted", Toast.LENGTH_SHORT).show()
            //open setting permissions screen
            Util.openAppPermissionSettings(requireContext())

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharLocation(args.charID)

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Progress -> {
                    Toast.makeText(requireContext(), "Still Loading", Toast.LENGTH_LONG).show()
                }

                is Resource.Success -> {
                    binding.id.text = it.data.id.toString()
                    binding.type.text = it.data.type
                    binding.locname.text = it.data.name


                    ///adapter setlist here
                }
            }
        }
        mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
    }

    fun startPerm(){
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private var marker: Marker? = null
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            //Result of location
            if (locationResult.locations.lastOrNull() != null) {
                val lat = locationResult.locations.first().latitude
                val long = locationResult.locations.first().longitude
                val location = LatLng(lat, long)

                println("$lat $long")

//                stopLocationUpdates()
                if (marker == null) {
                    val options = MarkerOptions().position(location).title("Marker")
                    marker = googleMap.addMarker(options)
                } else
                    marker?.position = location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }
    }

    private fun stopLocationUpdates() {
        fusedLocation.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        val locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
        //Start get Location updates
        fusedLocation.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        // Add a location cursor (marker) on the map
        val latitude = 30.0839459 // Replace with your desired latitude
        val longitude = 31.3198485 // Replace with your desired longitude

        getCurrentLocation()
        p0.isMyLocationEnabled = true
    }

    override fun onResume() {
        super.onResume()

        if (Util.isGpsEnabled(requireContext())) {
            startPerm()
        } else {
            Util.openGpsSettings(requireContext())
        }
    }

}

