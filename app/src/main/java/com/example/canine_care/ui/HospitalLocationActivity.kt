package com.example.canine_care.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.canine_care.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HospitalLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
        val AdvancedHospital = LatLng(27.7052354,85.327227)
        val LalitpurVetenary = LatLng(27.684876, 85.301007)
        val KathmanduVeterain = LatLng(27.6694546,85.2979249)
        val AnimalMedical = LatLng(27.72000709700021, 85.35147873903703)
        val DivineVetenary = LatLng(27.72000709700021, 85.35147873903703)
        val MobileVetenary = LatLng(27.673993882504714, 85.31185020338346)
        val PatanVetenary = LatLng(27.679172434045253, 85.30893174346991)



//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(AdvancedHospital).title("Advanced Pet Hospital and Research Center Pvt Ltd."))
        mMap.addMarker(MarkerOptions().position(LalitpurVetenary).title("Lalitpur Vetenary Hospital"))
        mMap.addMarker(MarkerOptions().position(KathmanduVeterain).title("Kathmandu Veterian Clinic"))
        mMap.addMarker(MarkerOptions().position(AnimalMedical).title("Animal Medical Center"))
        mMap.addMarker(MarkerOptions().position(DivineVetenary).title("Divine Vetenary Clinic"))
        mMap.addMarker(MarkerOptions().position(MobileVetenary).title("Mobile Vetenary Clinic"))
        mMap.addMarker(MarkerOptions().position(PatanVetenary).title("Patan Vetenary Hospital"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AdvancedHospital, 10.0f))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LalitpurVetenary))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KathmanduVeterain))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AnimalMedical))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DivineVetenary))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MobileVetenary))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PatanVetenary))
    }
}