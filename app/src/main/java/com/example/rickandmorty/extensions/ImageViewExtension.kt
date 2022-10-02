package com.example.rickandmorty.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R


fun AppCompatImageView.load(url: String?, width: Int, height: Int) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.img)
        .into(this)
}