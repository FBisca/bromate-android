package com.ledevs.bromate.app.ui.databinding

import android.databinding.BindingAdapter
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("bind:imageUrl")
fun loadImage(view: CircleImageView, url: String) {
  Picasso.with(view.context).load(url).into(view)
}

@BindingAdapter("app:srcCompat", "app:tint")
fun loadVectorDrawable(view: AppCompatImageView, @DrawableRes iconRes: Int, @ColorRes colorRes: Int?) {
  view.setImageResource(iconRes)

  val mutated = view.drawable?.let { DrawableCompat.wrap(it).mutate() }
  val color = colorRes?.let { ContextCompat.getColor(view.context, colorRes) }

  if (mutated != null && color != null) {
    DrawableCompat.setTint(mutated, color)
  }
}

@BindingAdapter("app:backgroundTint")
fun loadBackgroundTint(view: AppCompatImageView, @ColorRes colorRes: Int?) {
  val mutated = view.background?.let { DrawableCompat.wrap(it).mutate() }
  val color = colorRes?.let { ContextCompat.getColor(view.context, colorRes) }

  if (mutated != null && color != null) {
    DrawableCompat.setTint(mutated, color)
  }
}


