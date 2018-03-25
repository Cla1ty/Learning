package com.dwiariyanto.galleryview.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dwiariyanto.galleryview.MainActivity
import com.dwiariyanto.galleryview.R
import com.dwiariyanto.galleryview.gallery.adapter.GalleryAdapter
import com.dwiariyanto.galleryview.gallery.adapter.GalleryItem
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel
import com.dwiariyanto.galleryview.gallery.widget.GalleryToolbar
import kotlinx.android.synthetic.main.gallery_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment()
{
    private val adapter: GalleryAdapter by lazy { GalleryAdapter() }

    val presenter: IGalleryPresenter by lazy { GalleryPresenter(this) }
    val view: IGalleryView by lazy { GalleryView() }
    val galleryToolbar by lazy { (activity as MainActivity).galleryToolbar }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        galleryToolbar.setup(GalleryToolbarListener())

        presenter.getAllImage()
    }

    private fun setupAdapter()
    {
        galleryView.adapter = adapter

        adapter.galleryItem.also {
            it.presenter = presenter
            it.listener = GalleryItemListener()
        }
    }
    private inner class GalleryItemListener : GalleryItem.Listener
    {
        override fun onGalleryClicked(data: ImageGalleryModel)
        {
            presenter.updateImageSelected(data)
        }
    }

    private inner class GalleryView : IGalleryView
    {
        override fun onAllImageLoaded(listAllImageGallery: List<ImageGalleryModel>)
        {
            Log.d("TAG", listAllImageGallery.size.toString())
            adapter.data = listAllImageGallery
        }

        override fun onSelectedImageCountUpdated(count: Int)
        {
            galleryToolbar.updateTitle(count)
        }
    }

    private inner class GalleryToolbarListener : GalleryToolbar.Listener
    {
        override fun onClearIconClicked()
        {
            presenter.clearAllImageSelected()
            adapter.notifyDataSetChanged()
        }

        override fun onFinishButtonClicked()
        {
            Toast.makeText(context, "Button Finish", Toast.LENGTH_SHORT).show()
        }
    }
}