package com.arildojr.nubank.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arildojr.nubank.R
import com.arildojr.nubank.databinding.ActivityHomeBinding
import com.arildojr.nubank.enums.BottomCardsEnum
import com.arildojr.nubank.enums.MenuHomeEnum
import com.arildojr.nubank.ui.adapters.BottomCardsAdapter
import com.arildojr.nubank.ui.adapters.MenuHomeAdapter
import com.arildojr.nubank.utils.fadeVisibility

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        loadBottomCards()
        loadHomeMenu()

        binding.llTopHeader.setOnClickListener {
            binding.llHeaderAccountDetails.fadeVisibility(true)
            binding.llContainerBottomCards.fadeVisibility(false)
        }
    }

    private fun loadHomeMenu() {
        val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val adapter = MenuHomeAdapter(MenuHomeEnum.values().toList()) {

            when (it) {
                MenuHomeEnum.HELP_ME -> {
                }

                MenuHomeEnum.PROFILE -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }

                MenuHomeEnum.NUACCOUNT_SETTINGS -> {
                }

                MenuHomeEnum.APP_SETTINGS -> {
                }
            }
        }

        itemDecor.setDrawable(getDrawable(R.drawable.shape_item_divider)!!)
        binding.rvHomeMenu.addItemDecoration(itemDecor)
        binding.rvHomeMenu.setHasFixedSize(true)
        binding.rvHomeMenu.adapter = adapter
    }

    private fun loadBottomCards() {
        val adapter = BottomCardsAdapter(BottomCardsEnum.values().toList()) {

            when (it) {
                BottomCardsEnum.INDICATE_FRIEND -> {
                }
            }
        }
        binding.rvBottomCards.setHasFixedSize(true)
        binding.rvBottomCards.adapter = adapter
    }
}