package com.dwiariyanto.galleryview.gallery.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageGalleryModel(
        val id: Long,
        val title: String,
        val mimeType: String,
        val dateTaken: Long,
        val path: String,
        val orientation: Int,
        val bucketId: Int,
        val size: Long,
        val width: Int,
        val height: Int,
        val miniThumbMagic: Int,
        val uri: Uri
) : Parcelable
