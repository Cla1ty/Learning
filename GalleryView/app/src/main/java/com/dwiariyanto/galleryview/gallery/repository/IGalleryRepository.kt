package com.dwiariyanto.galleryview.gallery.repository

import android.graphics.Bitmap
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel

interface IGalleryRepository
{
    fun getDetailImage(pId: Long): ImageGalleryModel?
    fun getAllImage(): List<ImageGalleryModel>
    fun getThumbnail(pId: Long): Bitmap
}
