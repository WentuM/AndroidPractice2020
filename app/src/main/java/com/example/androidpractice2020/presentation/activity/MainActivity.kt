package com.example.androidpractice2020.presentation.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidpractice2020.R
import com.example.androidpractice2020.application.WeatherApplication
import com.example.androidpractice2020.data.LocationRepositoryImpl
import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.domain.FindCityUseCase
import com.example.androidpractice2020.presentation.presenter.MainPresenter
import com.example.androidpractice2020.presentation.recyclerview.CityAdapter
import com.example.androidpractice2020.presentation.view.MainView
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MvpAppCompatActivity(),
    MainView {

    private var citiesList: ArrayList<City> = arrayListOf()
    private var permissionGeoLocation: Boolean = false


    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPermissionGeoLocation()

        createSearchView()
    }

    private fun getPermissionGeoLocation() {
        val hasForegroundLocationPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (hasForegroundLocationPermission == PackageManager.PERMISSION_GRANTED) {
            permissionGeoLocation = true
        } else {
            val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
        }
    }

    private fun initListCities(arrayCity: ArrayList<City>) {
        if (permissionGeoLocation) {
            citiesList = arrayCity
        }
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

    private fun createSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(cityName: String?): Boolean {
                presenter.searchCityForName(cityName)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //вызовется при изменении ведённого текста
                return true
            }
        })
    }

    private fun initPresenter() =
        MainPresenter(
            locationRepositoryImpl = LocationRepositoryImpl(
                client = LocationServices.getFusedLocationProviderClient(this)
            ),
            findCityUseCase = FindCityUseCase(
                (application as WeatherApplication).repository, Dispatchers.IO
            )
        )

    override fun showLoading() {
        progress_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_list.visibility = View.GONE
    }

    override fun consumeError(throwable: Throwable) {
        throwable.message?.let {
            Snackbar.make(
                findViewById(android.R.id.content),
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun navigateToDetailActivity(id: Int) {
        if (id != -1) {
            val intent =
                Intent(this@MainActivity, DetailCityActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        } else {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Город с таким названием не найден",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun getListCities(arrayCity: ArrayList<City>) {
        initListCities(arrayCity)
    }

    override fun navigateToLogin() {}
}