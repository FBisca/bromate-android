package com.ledevs.bromate.app.ui.databinding

import android.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("bind:imageUrl")
fun loadImage(view: CircleImageView, url: String) {
  Picasso.with(view.context).load(url).into(view)
}
