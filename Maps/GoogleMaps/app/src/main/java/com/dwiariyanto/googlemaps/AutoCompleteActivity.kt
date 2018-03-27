package com.dwiariyanto.googlemaps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener

class AutoCompleteActivity : AppCompatActivity()
{
    private val TAG = AutoCompleteActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)

        val autocompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener
        {
            override fun onPlaceSelected(place: Place)
            {
                Log.i(TAG, "Place: " + place.name)
            }

            override fun onError(status: Status)
            {
                Log.i(TAG, "An error occurred: $status")
            }
        })
    }
}
