package com.dwiariyanto.galleryview

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import com.dwiariyanto.galleryview.gallery.GalleryFragment
import com.dwiariyanto.galleryview.gallery.widget.GalleryToolbar

class MainActivity : PermissionActivity()
{
    lateinit var galleryToolbar: GalleryToolbar
    var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        galleryToolbar = findViewById(R.id.galleryToolbar)

        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                pPermissionGranted = {
                    supportFragmentManager.beginTransaction()
                            .add(R.id.content, GalleryFragment(), "Gallery")
                            .commit()
                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item!!.itemId == android.R.id.home)
        {
            listener?.onHomeMenuClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    interface Listener{
        fun onHomeMenuClicked()
    }
}
