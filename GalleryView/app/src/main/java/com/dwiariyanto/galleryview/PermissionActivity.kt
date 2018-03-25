package com.dwiariyanto.galleryview

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log

abstract class PermissionActivity : AppCompatActivity()
{
    private var permissionGranted: (() -> Unit)? = null
    private var permissionDenied: (() -> Unit)? = null

    fun requestPermissions(pUsesPermission: Array<String>,
                           pPermissionGranted: (() -> Unit)? = null,
                           pPermissionDenied: (() -> Unit)? = null)
    {
        permissionGranted = pPermissionGranted
        permissionDenied = pPermissionDenied

        var lPermissionGranted = true
        for (lPermission in pUsesPermission)
        {
            if (ContextCompat.checkSelfPermission(this,
                            lPermission) == PackageManager.PERMISSION_DENIED
            )
            {
                Log.d("Permission", "REQUEST PERMISSION: " + lPermission.substring(19))

                if (lPermissionGranted)
                {
                    lPermissionGranted = false
                    ActivityCompat.requestPermissions(this, pUsesPermission, 0)
                }
            }
        }

        if (lPermissionGranted)
        {
            val builder = StringBuilder()
            for (lPermission in pUsesPermission)
            {
                builder.append("\n")
                builder.append(lPermission.substring(19))
            }

            Log.i("Permission", "===== PERMISSION GRANTED =====$builder\n===== end =====")
            pPermissionGranted?.invoke()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray)
    {
        if (requestCode == 0 && grantResults.isNotEmpty())
        {
            for (index in 0 until grantResults.size)
            {
                Log.i("Permission",
                        "${permissions[index].substring(19)}: ${if (grantResults[index] == -1) "DENIED" else "GRANTED"}")
            }

            val lPermissionGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (lPermissionGranted)
            {
                Log.i("Permission", "PERMISSION_GRANTED")
                permissionGranted?.invoke()
            }
            else
            {
                Log.i("Permission", "PERMISSION_DENIED")
                permissionDenied?.invoke()
            }
        }

        permissionGranted = null
        permissionDenied = null
    }
}
