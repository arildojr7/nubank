package com.arildojr.nubank.ui.activities

import android.os.Bundle
import com.arildojr.nubank.R
import com.arildojr.nubank.ui.customviews.StackActivity

class ProfileActivity : StackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}
