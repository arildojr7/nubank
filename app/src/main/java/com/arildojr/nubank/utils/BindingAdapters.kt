package com.arildojr.nubank.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:srcIcon")
fun ImageView.srcIcon(resourceId: Int?) {
    resourceId?.let { setImageResource(it) }
}
