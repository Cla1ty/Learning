package com.dwiariyanto.toolbar

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        homeAsUp()
        actionBar()
        title()
        customView()
        logo()
        menu()
        titleText()
        homeIcon()

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        nav_view.setNavigationItemSelectedListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setSupportActionBar(toolbar)
        supportActionBar?.also {
            it.addOnMenuVisibilityListener {
                Log.i("TAG", "Menu visible $it")
            }
            it.setIcon(R.drawable.ic_launcher_foreground)
            it.setLogo(R.drawable.ic_launcher_foreground)
            it.setCustomView(R.layout.context_menu)
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item?.itemId == android.R.id.home)
        {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private var homeIcon = false
    private fun homeIcon()
    {
        btn8.setOnClickListener {
            supportActionBar?.also {
                homeIcon = !homeIcon
                if (homeIcon)
                    it.setHomeAsUpIndicator(R.drawable.ic_home)
                else
                    it.setHomeAsUpIndicator(null)
            }
        }
    }

    private var counter = 0
    private fun titleText()
    {
        btn7.setOnClickListener {
            supportActionBar?.also {
                it.title = "Title ${++counter}"
            }
        }
    }

    private var showMenu = true
    private fun menu()
    {
        btn6.setOnClickListener {
            showMenu = !showMenu
            invalidateOptionsMenu()
        }
    }

    private var useLogo = false
    private fun logo()
    {
        btn5.setOnClickListener {
            supportActionBar?.also {
                useLogo = !useLogo
                it.setDisplayShowHomeEnabled(useLogo)
                it.setDisplayUseLogoEnabled(useLogo)
            }
        }
    }

    private var showCustomView = false
    private fun customView()
    {
        btn4.setOnClickListener {
            supportActionBar?.also {
                showCustomView = !showCustomView
                it.setDisplayShowCustomEnabled(showCustomView)
            }
        }
    }

    private var title = true
    private fun title()
    {
        btn3.setOnClickListener {
            supportActionBar?.also {
                title = !title
                it.setDisplayShowTitleEnabled(title)
            }
        }
    }

    private var showHide = true
    private fun actionBar()
    {
        btn2.setOnClickListener {
            supportActionBar?.also {
                if (showHide)
                    it.hide()
                else
                    it.show()

                showHide = !showHide
            }
        }
    }

    private var showHomeAsUp = false
    private fun homeAsUp()
    {
        btn1.setOnClickListener {
            supportActionBar?.also {
                showHomeAsUp = !showHomeAsUp
                it.setDisplayHomeAsUpEnabled(showHomeAsUp)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.search, menu)
        return showMenu
    }
}
