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
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.arildojr.nubank.ui.adapters.ViewPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        loadBottomCards()
        loadHomeMenu()
        setupFloatView()

        binding.llTopHeader.setOnClickListener {
            binding.llHeaderAccountDetails.fadeVisibility(!state)
            binding.footerContainer.llContainerBottomCards.fadeVisibility(state)
            state = !state
        }
    }

    private fun setupFloatView() {
        val viewPager = binding.floatView
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        CoroutineScope(Dispatchers.Main).launch {
            delay(100)
            binding.floatView.init(this@HomeActivity, binding.topGuideline.top, binding.footerGuideline.top)

            binding.floatView.position.observe(this@HomeActivity, Observer {
                binding.llHeaderAccountDetails.fadeVisibility((it).toFloat() / 100)
                binding.footerContainer.llContainerBottomCards.fadeVisibility(1 - it / 100.toFloat())
            })
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
        binding.footerContainer.rvBottomCards.setHasFixedSize(true)
        binding.footerContainer.rvBottomCards.adapter = adapter
    }
}