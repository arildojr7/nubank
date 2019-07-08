package com.arildojr.nubank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.nubank.R
import com.arildojr.nubank.databinding.ItemMenuHomeBinding
import com.arildojr.nubank.enums.MenuHomeEnum

interface OnClickMenu {
    fun onClickMenu(menu: MenuHomeEnum)
}

class MenuHomeAdapter(
    var list: List<MenuHomeEnum>, private val clickListener: (MenuHomeEnum) -> Unit
) : RecyclerView.Adapter<MenuHomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ItemMenuHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_menu_home,
            parent,
            false
        )

        return ViewHolder(view, clickListener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding: ItemMenuHomeBinding, val clickListener: (MenuHomeEnum) -> Unit) :
        RecyclerView.ViewHolder(binding.root), OnClickMenu {

        override fun onClickMenu(menu: MenuHomeEnum) {
            clickListener(menu)
        }

        fun bind(menu: MenuHomeEnum) {
            binding.menu = menu
            binding.onClick = this
        }
    }
}