package com.arildojr.nubank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.nubank.R
import com.arildojr.nubank.databinding.ItemBottomCardBinding
import com.arildojr.nubank.enums.BottomCardsEnum

class BottomCardsAdapter(
    var list: List<BottomCardsEnum>, private val clickListener: (BottomCardsEnum) -> Unit
) : RecyclerView.Adapter<BottomCardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ItemBottomCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_bottom_card,
            parent,
            false
        )

        return ViewHolder(view, clickListener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding: ItemBottomCardBinding, val clickListener: (BottomCardsEnum) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

         fun onClickMenu(menu: BottomCardsEnum) {
            clickListener(menu)
        }

        fun bind(menu: BottomCardsEnum) {
            binding.item = menu
        }
    }
}