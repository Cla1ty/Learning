package com.dwiariyanto.toolbar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        buttonMenu()
        buttonMenuRes()
    }

    private var menu = true
    private fun buttonMenu()
    {
        btn1.setOnClickListener {
            menu = !menu
            setHasOptionsMenu(menu)
        }
    }

    private var menuRes = R.menu.search
    private fun buttonMenuRes()
    {
        btn2.setOnClickListener {
            menuRes = when (menuRes)
            {
                0 -> R.menu.search
                else -> 0
            }
            activity.invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        menu?.clear()

        if (menuRes != 0)
        {
            inflater?.inflate(menuRes, menu)
        }
    }

} // Required empty public constructor
