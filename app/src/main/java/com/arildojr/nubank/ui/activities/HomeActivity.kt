package com.arildojr.nubank.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arildojr.nubank.R
import com.arildojr.nubank.databinding.ActivityHomeBinding
import com.arildojr.nubank.enums.MenuHomeEnum
import com.arildojr.nubank.ui.adapters.MenuHomeAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        loadHomeMenu()
    }

    private fun loadHomeMenu() {
        val itemDecor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val adapter = MenuHomeAdapter(MenuHomeEnum.values().toList()) {

            Log.e("::::", it.name)
        }

        itemDecor.setDrawable(getDrawable( R.drawable.shape_item_divider)!!)
        binding.rvHomeMenu.addItemDecoration(itemDecor)
        binding.rvHomeMenu.setHasFixedSize(true)

        binding.rvHomeMenu.adapter = adapter
    }
}