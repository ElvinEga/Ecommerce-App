package com.twisac.apps.amazin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product.*


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        setupProductView()

    }


    private fun setupProductView() {
        var name = intent.getStringExtra("name")
        var image = intent.getStringExtra("image")
        var description = intent.getStringExtra("description")
        var price = intent.getDoubleExtra("price",0.0)
        var id = intent.getIntExtra("id",1)

        val res = resources.getIdentifier(image, "drawable", packageName)
        iv_thumbnail.setImageResource(res)

        tv_name.text =name
        tv_price.text ="Ksh ${price.toString()}"
        tv_description.text =description

        iv_back.setOnClickListener {
            //go back
            super.onBackPressed()
        }
        iv_cart.setOnClickListener {
            //go to cart
            val intent = Intent(this@ProductActivity, CartActivity::class.java)
            finish()
            startActivity(intent)
        }

    }
}