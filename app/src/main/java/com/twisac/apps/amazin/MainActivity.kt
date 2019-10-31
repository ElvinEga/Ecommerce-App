package com.twisac.apps.amazin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.google.gson.reflect.TypeToken
import com.twisac.apps.amazin.adapters.HeaderAdapter
import com.twisac.apps.amazin.adapters.HomeAdapter
import com.twisac.apps.amazin.component.AlertPopup
import com.twisac.apps.amazin.models.Product
import com.twisac.apps.amazin.models.Slide
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Tint top white
        AlertPopup().statusBarTint(this@MainActivity)
        //top bar
        setupCollopsinBar()

        //recycler view
        setupRecyclerView()

    }

    private fun setupCollopsinBar() {
        //toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.title=""
        collapsing_toolbar.title = ""

        /** Setup AppBar
         *
         */
        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {

                    toolbar.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorToolBar))
                    tp_title.text = "Amazin"
                    isShow = true
                } else if (isShow) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorTransparent))
                    tp_title.text = "Amazin"
                    isShow = false
                }
            }
        })
        //set expand as default
        appbar.setExpanded(true)
    }

    private fun setupRecyclerView() {
        var slides: MutableList<Slide> = ArrayList()
        var gson = Gson()
        val listType = object : TypeToken<List<Slide>>() {}.type
       slides = gson.fromJson<MutableList<Slide>>(loadJSONFromAsset("slides.json"), listType)

        val headerAdapter = HeaderAdapter(this, slides)
        rv_top.setHasFixedSize(true)
        rv_top.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        val animationController = AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_slide_from_right)
        rv_top.layoutAnimation = animationController

        rv_top.adapter = headerAdapter
        headerAdapter.notifyDataSetChanged()
        rv_top.scheduleLayoutAnimation()

      /**
       *  Top recyclerviewTransition*/
        var ty = 0

        val task = object : TimerTask() {
            override fun run() {
                val items = headerAdapter.itemCount

               /* when { ty.compareTo(headerAdapter.itemCount) < 0 -> {
                    rv_top.smoothScrollToPosition(ty)
                    ty.inc()
                }
                    ty.equals(items) -> ty = 0
                }*/


              /*  if (ty.compareTo(headerAdapter.itemCount) < 0) {
                    rv_top.smoothScrollToPosition(ty)
                    ty.inc()

                    if (ty == items) ty = 0
                }*/
                if (ty < headerAdapter.itemCount) {

                    rv_top.smoothScrollToPosition(ty)
                    ty++

                    if (ty == items) ty = 0
                }


            }
        }
        val timer = Timer()
        val delay: Long = 0
        val intevalPeriod = (1 * 4000).toLong()
        timer.scheduleAtFixedRate(task, delay, intevalPeriod)

        var products: MutableList<Product> = ArrayList()
        val listTypeProduct = object : TypeToken<List<Product>>() {}.type
        products = gson.fromJson<MutableList<Product>>(loadJSONFromAsset("female_products.json"), listTypeProduct)

        val homeAdapter = HomeAdapter(this, products)
        rv_home.setHasFixedSize(true)
        rv_home.layoutManager = GridLayoutManager(this@MainActivity, 2)

        val animationController2 = AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_slide_from_bottom)
        rv_home.layoutAnimation = animationController2

        rv_home.adapter = homeAdapter
        homeAdapter.notifyDataSetChanged()
        rv_home.scheduleLayoutAnimation()




  }


    private fun loadJSONFromAsset(filename:String): String? {
        return try {

            val `is` = assets.open(filename)

            val size = `is`.available()

            val buffer = ByteArray(size)

            `is`.read(buffer)

            `is`.close()

            String(buffer,  charset("UTF-8"))

        } catch (ex: IOException) {ex.printStackTrace(); null
        }

    }
}