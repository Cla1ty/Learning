package com.dwiariyanto.backstack.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction

var counter = 0
fun FragmentActivity.transact(transact: FragmentTransaction.(tag: String) -> Unit)
{
    val tag = "counter ${++counter}"

    supportFragmentManager
            .beginTransaction()
            .also { transact.invoke(it, tag) }
            .commit()
}

fun FragmentActivity.traceBackStack(tag: String): String
{
    val stringBuilder = StringBuilder()
    stringBuilder.append("TAG = $tag")
    stringBuilder.append("\n")
    stringBuilder.append("BACK STACK")

    (0 until supportFragmentManager.backStackEntryCount)
            .forEach {
                val bs = supportFragmentManager
                        .getBackStackEntryAt(it)

                stringBuilder.append("\n")
                stringBuilder.append("fragment ${bs.name}")
            }

    stringBuilder.append("\n")
    stringBuilder.append("ACTIVE")

    supportFragmentManager.fragments.forEach {
        stringBuilder.append("\n")
        stringBuilder.append("Fragment ${it.tag} - ${it.isVisible}")

    }

    return stringBuilder.toString()
}

fun FragmentActivity.getFirstFragmentOrNull(): Fragment?
{
    return supportFragmentManager.fragments.firstOrNull()
}

fun FragmentActivity.getLastFragmentOrNull(): Fragment?
{
    return supportFragmentManager.fragments.lastOrNull()
}

fun FragmentActivity.getLastFragmentVisibleOrNull(): Fragment?
{
    return supportFragmentManager.fragments.lastOrNull { it.isVisible }
}

fun FragmentActivity.getLastBackStackFragmentOrNull(): Fragment?
{
    val lastIndex = supportFragmentManager.backStackEntryCount - 1
    if(lastIndex == -1) return null

   val tag = supportFragmentManager.getBackStackEntryAt(lastIndex).name
    return supportFragmentManager.findFragmentByTag(tag)
}