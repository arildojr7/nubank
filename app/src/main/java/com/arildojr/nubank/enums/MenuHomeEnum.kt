package com.arildojr.nubank.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.arildojr.nubank.R

enum class MenuHomeEnum(@DrawableRes val drawableId: Int?, @StringRes val title: Int, @StringRes val subTitle: Int = R.string.none) {
    HELP_ME(R.drawable.ic_help, R.string.help_me),
    PROFILE(R.drawable.ic_user, R.string.profile, R.string.profile_subtitle),
    NUACCOUNT_SETTINGS(R.drawable.ic_savings, R.string.nuaccount_settings),
    APP_SETTINGS(R.drawable.ic_phone, R.string.app_settings)
}