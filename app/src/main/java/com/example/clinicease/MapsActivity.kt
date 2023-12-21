package com.example.clinicease

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val FINE_PERMISSION_CODE: Int = 1
    private lateinit var currentLocation: android.location.Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var myMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_PERMISSION_CODE)
            return
        }

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location

                val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this@MapsActivity)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap

        val atual = LatLng(currentLocation.latitude, currentLocation.longitude)
        myMap.moveCamera(CameraUpdateFactory.newLatLng(atual))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atual, 12f))

        val markeratual = MarkerOptions().position(atual).title("Minha Localização")
        markeratual.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        myMap.addMarker(markeratual)

        val hrestauracao = LatLng(-8.05378, -34.86425)

        val markerhrestauracao = MarkerOptions().position(hrestauracao).title("Hospital da Restauração Gov. Paulo Guerra")
        markerhrestauracao.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        myMap.addMarker(markerhrestauracao)

        val hportuguesbv = LatLng(-8.111246255991684, -34.89206098653116)

        val markerhportuguesbv = MarkerOptions().position(hportuguesbv).title("Real Hospital Português de Boa Viagem")
        markerhportuguesbv.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        myMap.addMarker(markerhportuguesbv)

        val upamesc = LatLng(-8.12079457935857, -34.91387603406976)

        val markerupamesc = MarkerOptions().position(hportuguesbv).title("Unidade de Pronto Atendimento Maria Esther Souto Carvalho")
        markerupamesc.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        myMap.addMarker(markerupamesc)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                Toast.makeText(this, "A permissão de localização foi negada. Permita-a para prosseguir", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
