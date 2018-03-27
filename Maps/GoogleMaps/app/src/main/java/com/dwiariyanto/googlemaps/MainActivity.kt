package com.dwiariyanto.googlemaps

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlacePicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private val PLACE_PICKER_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonMaps()
        buttonPlacePicker()
        buttonCurrentPlace()
        buttonAutoComplete()
    }

    private fun buttonAutoComplete()
    {
        btnPlaceAutoComplete.setOnClickListener {
            val intent = Intent(this, AutoCompleteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buttonCurrentPlace()
    {
        btnCurrentPlace.setOnClickListener {
            val intent = Intent(this, CurrentPlaceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buttonMaps()
    {
        btnMaps.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buttonPlacePicker()
    {
        btnPlacePicker.setOnClickListener {
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    {
        if (requestCode == PLACE_PICKER_REQUEST)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val place = PlacePicker.getPlace(this, data)
                val toastMsg = String.format("Place: %s", place.name)
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show()
            }
        }
    }
}
