package com.example.movietime.data.glide

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageLoader {
    fun loadImage(imageView:ImageView,url:String)


    class Loader(private val context: Context) : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String) {
            Glide.with(context).load(url).into(imageView)
        }
    }

}