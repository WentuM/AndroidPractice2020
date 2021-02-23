package com.example.androidpractice2020.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidpractice2020.*
import com.example.androidpractice2020.factory.ApiFactory
import com.example.androidpractice2020.factory.InfoCity
import com.example.androidpractice2020.recyclerview.CityAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val api by lazy {
        ApiFactory.weatherApi
    }

    private var citiesList: ArrayList<InfoCity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                lifecycleScope.launch {
                    try {
                        p0?.let {
                            api.getWeather(it).let {
                                val intent =
                                    Intent(this@MainActivity, DetailCityActivity::class.java)
                                intent.putExtra("id", it.id)
                                startActivity(intent)
                            }
                        }
                    } catch (e: Exception) {
                        Snackbar.make(findViewById(android.R.id.content),
                            "Город с таким названием не найден",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //вызовется при изменении ведённого текста
                return true
            }
        })
    }

    override fun onResume() {
        locationConnect()
        super.onResume()
    }

    private fun loadCityAroundUser(context: Context, latitude: Double, longitude: Double) {
        lifecycleScope.launch {
            api.getCitiesWeather(latitude, longitude, 15).let {
                citiesList = it.list as ArrayList<InfoCity>

                val list = city_list
                list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                if (citiesList.size != 0) {
                    list.adapter =
                        CityAdapter(
                            citiesList
                        ) {
                            val intent = Intent(context, DetailCityActivity::class.java)
                            intent.putExtra("id", it)
                            intent.putExtra("size", citiesList.size)
                            startActivity(intent)
                        }
                }

            }
        }
    }

    private fun locationConnect() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        var longitude = 37.6156
        var latitude = 55.7522
        val hasForegroundLocationPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (hasForegroundLocationPermission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                var location: Location? = task.result
                if (location == null) {

                    loadCityAroundUser(this, latitude, longitude)

                } else {
                    longitude = location.longitude
                    latitude = location.latitude

                    loadCityAroundUser(this, latitude, longitude)

                }
            }

        } else {
            val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
        }
    }
}