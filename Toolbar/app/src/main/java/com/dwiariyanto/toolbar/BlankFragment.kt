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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        menu()
    }

    private var menu = true
    private fun menu()
    {
        btn1.setOnClickListener {
            menu = !menu
            setHasOptionsMenu(menu)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)
    {
        inflater?.inflate(R.menu.search, menu)
    }

} // Required empty public constructor
