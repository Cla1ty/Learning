package com.dwiariyanto.googlemaps

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places

class CurrentPlaceActivity : AppCompatActivity(),
    GoogleApiClient.OnConnectionFailedListener
{
    private val TAG = MapsActivity::class.java.simpleName
    private lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_auto_complete)

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build()

        currentPlace()
    }

    @SuppressLint("MissingPermission")
    private fun currentPlace()
    {
        val result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null)

        result.setResultCallback { likelyPlaces ->
            for (placeLikelihood in likelyPlaces)
            {
                Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                        placeLikelihood.place.name,
                        placeLikelihood.likelihood))
            }
            likelyPlaces.release()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult)
    {

    }
}
