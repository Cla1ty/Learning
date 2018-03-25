package com.dwiariyanto.galleryview.gallery.adapter

import android.view.View
import com.dwiariyanto.galleryview.R
import com.dwiariyanto.galleryview.gallery.IGalleryPresenter
import com.dwiariyanto.galleryview.gallery.data.ImageGalleryModel
import com.dwiariyanto.galleryview.utils.GlideApp
import com.dwiariyanto.recyclerview.adapter.BaseItemView
import com.dwiariyanto.recyclerview.adapter.RecyclerViewHolder
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryItem
    : BaseItemView<ImageGalleryModel>(
        ImageGalleryModel::class.java,
        R.layout.gallery_item
)
{
    lateinit var presenter: IGalleryPresenter
    lateinit var listener: Listener

    fun setup(presenter: IGalleryPresenter, listener: Listener)
    {
        this.presenter = presenter
        this.listener = listener
    }

    override fun onBind(holder: RecyclerViewHolder, data: ImageGalleryModel)
    {
        with(holder.itemView) {
            GlideApp.with(context)
                    .load(data.path)
                    .centerCrop()
                    .into(ivGallery)

            if (presenter.isImageSelected(data))
            {
                laySelected.visibility = View.VISIBLE
            }
            else
            {
                laySelected.visibility = View.GONE
            }

            setOnClickListener {
                listener?.onGalleryClicked(data)
                onBind(holder, data)
            }
        }
    }

    interface Listener
    {
        fun onGalleryClicked(data: ImageGalleryModel)
    }
}
