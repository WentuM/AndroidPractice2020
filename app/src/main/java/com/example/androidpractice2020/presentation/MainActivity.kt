package com.example.androidpractice2020.presentation

import android.Manifest
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
import com.example.androidpractice2020.R
import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.application.WeatherApplication
import com.example.androidpractice2020.domain.FindCityUseCase
import com.example.androidpractice2020.presentation.recyclerview.CityAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var findCityUseCase: FindCityUseCase

    private var citiesList: ArrayList<City> = arrayListOf()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findCityUseCase =
            FindCityUseCase((application as WeatherApplication).repository, Dispatchers.IO)
        getPermissionGeoLocation()

        createSearchView()
    }

    fun loadCityAroundUser(
        latitude: Double,
        longitude: Double,
        //на будущее, если число городов будет меняться по решению пользователя
        countCities: Int
    ) {
        lifecycleScope.launch {
            findCityUseCase.findWeatherCitiesByCoordinates(latitude, longitude, countCities)
                .let {
                    citiesList = it

                    val list = city_list
                    list.addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )

                    if (citiesList.size != 0) {
                        list.adapter =
                            CityAdapter(
                                citiesList
                            ) {
                                val intent =
                                    Intent(this@MainActivity, DetailCityActivity::class.java)
                                intent.putExtra("id", it)
                                intent.putExtra("size", citiesList.size)
                                startActivity(intent)
                            }
                    }
                }
        }
    }

    private fun createSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(cityName: String?): Boolean {
                lifecycleScope.launch {
                    cityName?.let {
                        findCityUseCase.findWeatherCityByName(cityName).let {
                            if (it != null && it.id != -1) {
                                val intent =
                                    Intent(this@MainActivity, DetailCityActivity::class.java)
                                intent.putExtra("id", it.id)
                                startActivity(intent)
                            } else {
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Город с таким названием не найден",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
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

    private fun getPermissionGeoLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        var longitude = 37.6156
        var latitude = 55.7522
        val hasForegroundLocationPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (hasForegroundLocationPermission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                var location: Location? = task.result
                if (location == null) {

                    loadCityAroundUser(latitude, longitude, 15)
                } else {
                    longitude = location.longitude
                    latitude = location.latitude

                    loadCityAroundUser(latitude, longitude, 15)

                }
            }

        } else {
            val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
        }
//        var latitude = 0.0
//        var longitude = 0.0
//        val hasForegroundLocationPermission =
//            ContextCompat.checkSelfPermission(
//                applicationContext,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//        if (hasForegroundLocationPermission == PackageManager.PERMISSION_GRANTED) {
//            findCityUseCase.getCoordinatesByUser(this@MainActivity, true).let {
//                latitude = it[0]
//                longitude = it[1]
//                Log.e("qweqw2", "$latitude")
//                Log.e("qweqwe2", "$longitude")
//            }
//            loadCityAroundUser(latitude, longitude, 15)
//        } else {
//            loadCityAroundUser(55.7522, 37.6156, 15)
//            val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
//            ActivityCompat.requestPermissions(
//                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                REQUEST_CODE_PERMISSION_READ_CONTACTS
//            )
//        }
    }
}