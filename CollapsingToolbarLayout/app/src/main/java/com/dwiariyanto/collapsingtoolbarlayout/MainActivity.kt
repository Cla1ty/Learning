package com.dwiariyanto.collapsingtoolbarlayout

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initFab()
        initToolbar()
    }

    override fun onEnterAnimationComplete()
    {
        super.onEnterAnimationComplete()
        setRecyclerViewAdapter()
        recyclerView.scheduleLayoutAnimation()
    }

    private fun setRecyclerViewAdapter()
    {
        val data = ArrayList<ViewModel>()
        (1..10).map { it.toString() }.forEach {
            data.add(ViewModel(it, R.drawable.ic_launcher_background))
        }

        (recyclerView.adapter as Adapter).data = data

    }

    private fun initAdapter()
    {
        val adapter = Adapter()
        adapter.itemView.onClick = { viewTransition, viewModel ->
        }
        recyclerView.adapter = adapter
    }

    private fun initFab()
    {
        fab.setOnClickListener {
            Snackbar.make(content, "FAB Clicked", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initToolbar()
    {
        setSupportActionBar(toolbar)
    }
}
