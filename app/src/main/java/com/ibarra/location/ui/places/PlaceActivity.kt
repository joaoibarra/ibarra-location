package com.ibarra.location.ui.places

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.material.tabs.TabLayout
import com.ibarra.location.R
import com.ibarra.location.databinding.ActivityPlaceBinding
import kotlinx.android.synthetic.main.activity_place.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceActivity : AppCompatActivity() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val placeViewModel: PlaceViewModel by viewModel()

    companion object {
        const val ACCESS_LOCATION_PERMISSION = 27
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val binding: ActivityPlaceBinding = DataBindingUtil.setContentView(this@PlaceActivity, R.layout.activity_place)

        binding.viewModel = placeViewModel
        binding.lifecycleOwner = this

        tabs.addOnTabSelectedListener(object:
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                placeViewModel.onTabItemClick(tab?.text.toString())
            }
        })
    }

    override fun onStart() {
        super.onStart()
        checkUserPermission()
    }

    private fun checkUserPermission() {
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) ,
                ACCESS_LOCATION_PERMISSION )

        } else {
            getUserLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == ACCESS_LOCATION_PERMISSION
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getUserLocation()

        }
    }

    private fun getUserLocation() {
        fusedLocationProviderClient?.lastLocation?.addOnSuccessListener { location : Location? ->
            location?.let {
                placeViewModel.updateLocation(location.latitude,location.longitude)
            }
        }?.addOnFailureListener {

        }
    }
}
