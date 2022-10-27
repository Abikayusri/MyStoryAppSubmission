package abika.sinau.mystoryapp.ui.maps

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.base.BaseActivity
import abika.sinau.core.utils.toastShort
import abika.sinau.mystoryapp.R
import abika.sinau.mystoryapp.databinding.ActivityMapsBinding
import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.IOException
import java.util.*
import abika.sinau.core.R as coreR

@AndroidEntryPoint
class MapsActivity : BaseActivity<ActivityMapsBinding>(),
    OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val viewModel by viewModels<MapsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupMaps()
        setupObserver()
    }

    private fun setupMaps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        var mapFragment = SupportMapFragment()
        if (supportFragmentManager.findFragmentById(R.id.fMap) == null) {
            supportFragmentManager.beginTransaction().add(R.id.fMap, mapFragment).commit()
        } else {
            mapFragment = supportFragmentManager.findFragmentById(R.id.fMap) as SupportMapFragment
        }

        mapFragment.getMapAsync(this)
    }

    private fun setupObserver() {
        viewModel.apply {
            resultListStory.observe(this@MapsActivity) { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.listStory?.let {
                            if (it.isNotEmpty()) {
                                createMarker(it)
                            }
                        }
                    }
                    is Resource.Error -> {
                        toastShort(response.message.toString())
                    }
                    is Resource.Loading -> {}
                }
            }
        }
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
        getMyLocation()
        setMapStyle()
    }

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityMapsBinding {
        return ActivityMapsBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        viewModel.getListStory()
    }

    // region function untuk getMyLocation()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // endregion

    // region getAddressName function

    private fun getAddressName(lat: Double, lon: Double): String? {
        var addressName: String? = null
        val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
        try {
            val list = geocoder.getFromLocation(lat, lon, 1)
            if (list != null && list.size != 0) {
                addressName = list[0].getAddressLine(0)
                Timber.d("getAddressName: $addressName")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return addressName
    }

    // endregion

    // region setMapStyle function

    private fun setMapStyle() {
        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, coreR.raw.map_style))
            if (!success) {
                Timber.e("Style parsing failed.")
            }
        } catch (exception: Resources.NotFoundException) {
            Timber.e(exception, "Can't find style. Error: ")
        }

    }
    // endregion

    // region createMarker

    private val boundsBuilder = LatLngBounds.Builder()

    private fun createMarker(data: List<StoryListResponse>) {
        data.forEach { response ->
            val latitude = response.lat ?: 0.0
            val longitude: Double = response.lon ?: 0.0

            val latLong = LatLng(latitude, longitude)
            val addressName = getAddressName(latitude, longitude)
            mMap.addMarker(
                MarkerOptions().position(latLong).title(response.name).snippet(addressName)
            )
            boundsBuilder.include(latLong)
        }

        val bounds: LatLngBounds = boundsBuilder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300
            )
        )
    }

    // endregion
}