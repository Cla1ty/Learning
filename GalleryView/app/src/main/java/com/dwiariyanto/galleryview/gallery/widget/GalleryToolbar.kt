package com.dwiariyanto.galleryview.gallery.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.dwiariyanto.galleryview.R

class GalleryToolbar : Toolbar
{
    private lateinit var supportActionBar: ActionBar
    private lateinit var listener: Listener

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init
    {
        inflateMenu(R.menu.gallery_menu)
        val itemFinish = menu.findItem(R.id.menuFinish)
        val buttonFinish = itemFinish.actionView as AppCompatButton

        with(buttonFinish){
            val color = ContextCompat.getColor(context, R.color.colorPrimary)
            supportBackgroundTintList = ColorStateList.valueOf(color)
            text = "Selesai"

            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            setOnClickListener { listener.onFinishButtonClicked() }
        }


        setNavigationIcon(R.drawable.ic_clear)
        setNavigationOnClickListener { listener.onClearIconClicked() }
    }

    fun setup(listener: Listener)
    {
        this.listener = listener
    }

    fun updateTitle(selectedCount: Int)
    {
        title = "$selectedCount selected"
    }

    interface Listener
    {
        fun onClearIconClicked()
        fun onFinishButtonClicked()
    }
}
