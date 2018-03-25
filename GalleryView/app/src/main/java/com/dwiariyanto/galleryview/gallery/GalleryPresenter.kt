package com.dwiariyanto.galleryview.gallery

import android.os.AsyncTask
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel
import com.dwiariyanto.galleryview.gallery.repository.GalleryRepository
import com.dwiariyanto.galleryview.gallery.utils.SelectedList

class GalleryPresenter(
        fragment: GalleryFragment
) : IGalleryPresenter
{
    private val repository = GalleryRepository(fragment.context!!)
    private val view: IGalleryView = fragment.view
    private val selectedList: SelectedList by lazy { SelectedList() }

    override fun getAllImage()
    {
        LoadGalleryAsync(repository, view).execute()
    }

    override fun isImageSelected(model: ImageGalleryModel): Boolean
    {
        return selectedList.isImageSelected(model)
    }

    override fun updateImageSelected(model: ImageGalleryModel)
    {
        selectedList.addOrRemove(model)
        view.onSelectedImageCountUpdated(selectedList.size)
    }

    override fun clearAllImageSelected()
    {
        selectedList.clear()
        view.onSelectedImageCountUpdated(selectedList.size)
    }

    private class LoadGalleryAsync(
            private val repository: GalleryRepository,
            private val view: IGalleryView

    ) : AsyncTask<Void, Void, List<ImageGalleryModel>>()
    {
        override fun doInBackground(vararg params: Void?): List<ImageGalleryModel>
        {
            return repository.getAllImage()
        }

        override fun onPostExecute(result: List<ImageGalleryModel>?)
        {
            view.onAllImageLoaded(result!!)
        }
    }
}
