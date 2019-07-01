package com.arildojr.nubank.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.arildojr.nubank.R

enum class BottomCardsEnum(@DrawableRes val drawableId: Int?, @StringRes val title: Int, val order: Int = 0) {
    INDICATE_FRIEND(R.drawable.ic_help, R.string.help_me),
    SPLIT_BILL(R.drawable.ic_user, R.string.profile, R.string.profile_subtitle),
    DEPOSIT(R.drawable.ic_savings, R.string.nuaccount_settings),
    TRANSFER(R.drawable.ic_phone, R.string.app_settings),
    PAY(R.drawable.ic_phone, R.string.app_settings),
    BLOCK_CARD(R.drawable.ic_phone, R.string.app_settings)
}