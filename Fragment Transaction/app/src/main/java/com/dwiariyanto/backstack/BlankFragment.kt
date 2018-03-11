package com.dwiariyanto.backstack

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwiariyanto.backstack.extension.*
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment()
{
    private val lifecycleObserver = GenericLifecycleObserver { source, event ->
        Logcat.clear()

        val tag = (source as Fragment).tag
        Log.i(tag, event.name)

        if (event == Lifecycle.Event.ON_START ||
            event == Lifecycle.Event.ON_STOP ||
            event == Lifecycle.Event.ON_DESTROY
        )
        {
            textTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        buttonReplaceWithBackStack()
        buttonReplaceWithoutBackStack()

        buttonAddWithBackStack()
        buttonAddWithoutBackStack()

        buttonRemoveFirst()
        buttonRemoveLast()

        buttonShowLast()
        buttonHideLast()

        buttonAttachLast()
        buttonDetachLast()

        textTag()
    }

    private fun textTag()
    {
        text.text = tag
    }

    private fun textTrace()
    {
        (activity as MainActivity).textTrace()
    }

    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
        lifecycle.addObserver(lifecycleObserver)
    }

    override fun onDetach()
    {
        super.onDetach()
        lifecycle.removeObserver(lifecycleObserver)
    }

    private fun buttonReplaceWithBackStack()
    {
        btn1.setOnClickListener {
            activity.transact { tag ->
                this.replace(R.id.content, BlankFragment(), tag)
                this.addToBackStack(tag)
            }
        }
    }

    private fun buttonReplaceWithoutBackStack()
    {
        btn2.setOnClickListener {
            activity.transact { tag ->
                this.replace(R.id.content, BlankFragment(), tag)
            }
        }
    }

    private fun buttonAddWithBackStack()
    {
        btn8.setOnClickListener {
            activity.transact { tag ->
                this.add(R.id.content, BlankFragment(), tag)
                this.addToBackStack(tag)
            }
        }
    }

    private fun buttonAddWithoutBackStack()
    {
        btn3.setOnClickListener {
            activity.transact { tag ->
                this.add(R.id.content, BlankFragment(), tag)
            }
        }
    }

    private fun buttonRemoveFirst()
    {
        btn4.setOnClickListener {
            activity.transact { tag ->
                this.remove(activity.getFirstFragmentOrNull())
            }
        }
    }

    private fun buttonRemoveLast()
    {
        btn5.setOnClickListener {
            activity.transact { tag ->
                this.remove(activity.getLastFragmentOrNull())
            }
        }
    }

    private fun buttonShowLast()
    {
        btn6.setOnClickListener {
            activity.transact { tag ->
                this.show(activity.getLastFragmentOrNull())

                textTrace()
            }
        }
    }

    private fun buttonHideLast()
    {
        btn7.setOnClickListener {
            activity.transact { tag ->
                this.hide(activity.getLastFragmentOrNull())

                textTrace()
            }
        }
    }

    private fun buttonAttachLast()
    {
        btn9.setOnClickListener {
            activity.transact { tag ->
                this.attach(activity.getLastBackStackFragmentOrNull())

                textTrace()
            }
        }
    }

    private fun buttonDetachLast()
    {
        btn10.setOnClickListener {
            activity.transact { tag ->
                this.detach(activity.getLastBackStackFragmentOrNull())

                textTrace()
            }
        }
    }

}