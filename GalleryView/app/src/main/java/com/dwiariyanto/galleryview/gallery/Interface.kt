package com.dwiariyanto.galleryview.gallery

import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel

interface IGalleryView
{
    fun onAllImageLoaded(listAllImageGallery: List<ImageGalleryModel>)
    fun onSelectedImageCountUpdated(count: Int)
}

interface IGalleryPresenter
{
    fun getAllImage()
    fun isImageSelected(model:ImageGalleryModel):Boolean
    fun updateImageSelected(model: ImageGalleryModel)
    fun clearAllImageSelected()
}
