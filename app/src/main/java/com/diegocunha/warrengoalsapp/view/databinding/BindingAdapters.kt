package com.diegocunha.warrengoalsapp.view.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.diegocunha.warrengoalsapp.R


@BindingAdapter("visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("loadImageURL")
fun loadImageURL(view: ImageView, posterPath: String?) {
    posterPath.let {
        Glide
                .with(view.context)
                .load(posterPath)
                .error(R.drawable.ic_image_placeholder)
                .into(view)
    }
}

