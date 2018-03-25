package com.dwiariyanto.geocoderlistaddress

import android.app.IntentService
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.ResultReceiver
import android.support.annotation.Nullable
import android.text.TextUtils
import android.util.Log
import java.util.*

class AddressesByNameIntentService : IntentService("AddressesByNameIntentService")
{
    private val IDENTIFIER = "AddressesByNameIS"
    private var addressResultReceiver: ResultReceiver? = null


    override fun onHandleIntent(@Nullable intent: Intent?)
    {
        var msg = ""
        addressResultReceiver = intent!!.getParcelableExtra("address_receiver")

        if (addressResultReceiver == null)
        {
            Log.e(IDENTIFIER,
                    "No receiver in intent")
            return
        }

        val addressName = intent.getStringExtra("address_name")

        if (addressName == null)
        {
            msg = "No name found"
            sendResultsToReceiver(0, msg, null)
            return
        }

        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses: List<Address>? = null

        try
        {
            addresses = geocoder.getFromLocationName(addressName, 5)
        }
        catch (ioException: Exception)
        {
            Log.e("", "Error in getting addresses for the given name")
        }

        if (addresses == null || addresses.isEmpty())
        {
            msg = "No address found for the address name"
            sendResultsToReceiver(1, msg, null)
        }
        else
        {
            Log.d(IDENTIFIER, "number of addresses received " + addresses.size)
            val addressList = arrayOfNulls<String>(addresses.size)
            var j = 0
            for (address in addresses)
            {
                val addressInfo = ArrayList<String>()
                for (i in 0..address.maxAddressLineIndex)
                {
                    addressInfo.add(address.getAddressLine(i))
                }
                addressList[j] = TextUtils.join(System.getProperty("line.separator"),
                        addressInfo)
                Log.d(IDENTIFIER, addressList[j])
                j++
            }
            sendResultsToReceiver(2, "", addressList)
        }
    }

    private fun sendResultsToReceiver(resultCode: Int, message: String, addressList: Array<String?>?)
    {
        val bundle = Bundle()
        bundle.putString("msg", message)
        bundle.putStringArray("addressList", addressList)
        addressResultReceiver!!.send(resultCode, bundle)
    }
}
