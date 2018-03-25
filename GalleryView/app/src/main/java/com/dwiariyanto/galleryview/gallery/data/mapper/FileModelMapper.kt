package com.dwiariyanto.galleryview.gallery.data.mapper

import android.database.Cursor
import android.net.Uri
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel
import java.io.File

fun Cursor.toFileModel(): ImageGalleryModel
{
    val path = getString(4)

    return ImageGalleryModel(
            id = getLong(0),
            title = getString(1),
            mimeType = getString(2),
            dateTaken = getLong(3),
            path = path,
            orientation = getInt(5),
            bucketId = getInt(6),
            size = getLong(7),
            width = getInt(8),
            height = getInt(9),
            miniThumbMagic = getInt(10),
            uri = Uri.fromFile(File(path))
    )
}
