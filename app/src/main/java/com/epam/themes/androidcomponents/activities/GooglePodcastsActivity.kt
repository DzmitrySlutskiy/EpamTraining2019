package com.epam.themes.androidcomponents.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Menu
import android.view.MenuInflater
import com.epam.cleancodetest.R
import android.widget.TextView
import java.lang.reflect.AccessibleObject.setAccessible



class GooglePodcastsActivity : AppCompatActivity() {

    private lateinit var toolbar : Toolbar;
    private lateinit var toolbarTitle : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_podcasts)


        toolbar = findViewById<Toolbar>(R.id.google_podcasts_toolbar_root)
        setSupportActionBar(toolbar)
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title) as TextView

        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_searched_for_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setGoogleTitle()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_google_podcasts, menu)
        return true
    }

    private fun setGoogleTitle() {
        val builder = SpannableStringBuilder()
        val coloredTitle = SpannableString(getString(R.string.google_podcasts))
        coloredTitle.setSpan( ForegroundColorSpan(Color.BLUE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        coloredTitle.setSpan( ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        coloredTitle.setSpan( ForegroundColorSpan(Color.YELLOW), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        coloredTitle.setSpan( ForegroundColorSpan(Color.BLUE), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        coloredTitle.setSpan( ForegroundColorSpan(Color.BLUE), 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        coloredTitle.setSpan( ForegroundColorSpan(Color.RED), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(coloredTitle);
        toolbarTitle.setText( builder )

        /* try later figure out how to change layout in code
        var titleTextView: TextView? = null
        try {
            val f = toolBar.javaClass.getDeclaredField("mTitleTextView")
            f.setAccessible(true)
            titleTextView = f.get(toolBar) as TextView?

        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }
        titleTextView?.gravity = CENTER_HORIZONTAL
        */
    }
}