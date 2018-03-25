package com.dwiariyanto.galleryview.gallery.adapter

import android.support.v7.widget.RecyclerView
import com.dwiariyanto.recyclerview.adapter.BaseRecyclerViewAdapter
import com.dwiariyanto.recyclerview.extension.erViManager

class GalleryAdapter(
        val galleryItem: GalleryItem = GalleryItem()
) : BaseRecyclerViewAdapter(galleryItem)
{
    override fun build(recyclerView: RecyclerView)
    {
        with(recyclerView) {
            erViManager { erViCount = 3 }
        }
    }
}
