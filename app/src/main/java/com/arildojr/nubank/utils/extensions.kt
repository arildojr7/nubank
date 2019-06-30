package com.arildojr.nubank.utils

import android.view.View

fun View.fadeVisibility(show: Boolean) {
    val animationTime = this.context.resources.getInteger(android.R.integer.config_mediumAnimTime)
    this.apply {
        if (show) {
            alpha = 0f
            animate().withStartAction { visibility = View.VISIBLE }
            animate().alpha(1f).duration = animationTime.toLong()
        } else {
            alpha = 1f
            animate().alpha(0f).duration = animationTime.toLong()
            animate().withEndAction { visibility = View.INVISIBLE }
        }
    }
}