package com.example.pokemontask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokemontask.R

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 16/12/2021 created by Just clean
 */
class DataBindingAdapterUtils {
    companion object{
        @JvmStatic
        @BindingAdapter("app:loadImage")
        fun loadImage(view: ImageView, url: String?) {
            url?.apply {
                Glide.with(view)
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(view)
            }
        }
    }
}