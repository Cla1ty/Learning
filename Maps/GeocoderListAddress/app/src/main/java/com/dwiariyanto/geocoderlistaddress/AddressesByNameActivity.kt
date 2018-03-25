package com.dwiariyanto.geocoderlistaddress

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class AddressesByNameActivity : AppCompatActivity()
{

    private var addressResultReceiver: AddressListResultReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.subtitle = "Addresses By Name"

        addressResultReceiver = AddressListResultReceiver(Handler())
    }

    fun getAddressesByName(view: View)
    {
        getAddresses(addresses_t.text.toString())
    }

    private fun getAddresses(addName: String)
    {
        if (!Geocoder.isPresent())
        {
            Toast.makeText(this,
                    "Can't find address, ",
                    Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, AddressesByNameIntentService::class.java)
        intent.putExtra("address_receiver", addressResultReceiver)
        intent.putExtra("address_name", addName)
        startService(intent)
    }

    private inner class AddressListResultReceiver internal constructor(handler: Handler) :
        ResultReceiver(handler)
    {

        protected override fun onReceiveResult(resultCode: Int, resultData: Bundle)
        {

            if (resultCode == 0)
            {
                Toast.makeText(this@AddressesByNameActivity,
                        "Enter address name, ",
                        Toast.LENGTH_SHORT).show()
                return
            }

            if (resultCode == 1)
            {
                Toast.makeText(this@AddressesByNameActivity,
                        "Address not found, ",
                        Toast.LENGTH_SHORT).show()
                return
            }

            val addressList = resultData.getStringArray("addressList")
            showResults(addressList)
        }
    }

    private fun showResults(addressList: Array<String>?)
    {
        val arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, addressList!!)
        addresses_lst.adapter = arrayAdapter
    }
}
