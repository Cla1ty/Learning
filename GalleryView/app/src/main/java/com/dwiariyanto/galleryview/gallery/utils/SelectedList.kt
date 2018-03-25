package com.dwiariyanto.galleryview.gallery.utils

import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel

class SelectedList
{
    private val list = ArrayList<ImageGalleryModel>()

    val size: Int
        get() = list.size

    fun isImageSelected(model: ImageGalleryModel): Boolean
    {
        return list.contains(model)
    }

    fun addOrRemove(imageGalleryModel: ImageGalleryModel)
    {
        if (list.contains(imageGalleryModel))
        {
            list.remove(imageGalleryModel)
        }
        else
        {
            list.add(imageGalleryModel)
        }
    }

    fun clear()
    {
        list.clear()
    }
}
