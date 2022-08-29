package com.example.palettedemo

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clMain = findViewById<ConstraintLayout>(R.id.cl_main_activity)
        val imageMain = findViewById<ImageView>(R.id.iv_main)

        Glide.with(this)
            .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/6000ca11-0422-48c9-9254-65f6ba392a7c/dbqjnrt-005c6493-ffea-4c87-acb0-0b7929cb7df5.png/v1/fill/w_1192,h_670,q_75,strp/itachi_uchiha__naruto__minimalist_by_max028-dbqjnrt.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl0sIm9iaiI6W1t7InBhdGgiOiIvZi82MDAwY2ExMS0wNDIyLTQ4YzktOTI1NC02NWY2YmEzOTJhN2MvZGJxam5ydC0wMDVjNjQ5My1mZmVhLTRjODctYWNiMC0wYjc5MjljYjdkZjUucG5nIiwid2lkdGgiOiI8PTExOTIiLCJoZWlnaHQiOiI8PTY3MCJ9XV19.nWU3Wv8zYaNj0Dw5lvhJmgZ9FAH5iBB7LtLI9TbxlHg")
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Log.e("TAG", "Error Loading image", e)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?,isFirstResource: Boolean): Boolean {
                    Palette.from(resource!!.toBitmap())
                        .generate{
                            palette ->
                            palette?.let {
//                                Different ways to get different sort of colours
//                                val intColor = it.vibrantSwatch?.rgb?:0 // Returns color as int
                                val intColor = it.dominantSwatch?.rgb?:0
                                clMain.setBackgroundColor(intColor)
                            }
                        }
                    return false
                }
            }).into(imageMain)
    }
}









