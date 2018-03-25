package com.dwiariyanto.galleryview.gallery.repository

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel
import com.dwiariyanto.galleryview.gallery.data.mapper.toFileModel

class GalleryRepository(
        private val context: Context) : IGalleryRepository
{
    private val COLUMNS = arrayOf(MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.MIME_TYPE,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.ORIENTATION,
            MediaStore.Images.ImageColumns.BUCKET_ID,
            MediaStore.Images.ImageColumns.SIZE,
            MediaStore.Images.ImageColumns.WIDTH,
            MediaStore.Images.ImageColumns.HEIGHT,
            MediaStore.Images.ImageColumns.MINI_THUMB_MAGIC)

    private val IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    override fun getDetailImage(pId: Long): ImageGalleryModel?
    {
        val cursor = context.contentResolver.query(
                IMAGE_URI,
                COLUMNS,
                MediaStore.Files.FileColumns._ID + "=?",
                arrayOf(pId.toString()),
                null)
        var lImageGalleryModel: ImageGalleryModel? = null
        cursor.use {
            lImageGalleryModel = it.toFileModel()
        }
        return lImageGalleryModel
    }

    override fun getAllImage(): List<ImageGalleryModel>
    {
        val lCursor = context.contentResolver.query(
                IMAGE_URI,
                COLUMNS,
                null,
                null,
                "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC, " +
                "${MediaStore.Images.ImageColumns._ID} DESC")

        val data = ArrayList<ImageGalleryModel>()
        lCursor.use {
            while (it.moveToNext())
            {
                data.add(it.toFileModel())
            }
        }

        return data.toList()
    }

    override fun getThumbnail(pId: Long): Bitmap
    {
        return MediaStore.Images.Thumbnails.getThumbnail(
                context.applicationContext.contentResolver,
                pId,
                MediaStore.Images.Thumbnails.MICRO_KIND,
                null)
    }

}
