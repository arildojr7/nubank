package com.arildojr.nubank.models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.nubank.R
import com.arildojr.nubank.databinding.ItemMenuHomeBinding
import com.arildojr.nubank.enums.MenuHomeEnum

interface OnClickMenu {
    fun onClick(menu: MenuHomeEnum)
}

class MenuHomeAdapter(val context: Context, var list: List<MenuHomeEnum>) :
    RecyclerView.Adapter<MenuHomeAdapter.ViewHolder>(), OnClickMenu {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ItemMenuHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_menu_home,
            parent,
            false
        )

        return ViewHolder(view)

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onClick(menu: MenuHomeEnum) {
//        val args = Bundle()
//        args.putString("uuidToCardsLoginScreen", "fd")
//
//        var intent: Intent? = null
//        var overrideTransition = false
//
//        when (menu) {
//            MenuHomeEnum.APP_SETTINGS -> intent = Intent(context, HomeActivity::class.java)
//
//            else -> {
//            }
//        }
//        intent?.let {
//            (context as HomeActivity).startActivity(it)
//
//            if (overrideTransition) {
//                context.overridePendingTransition(
//                    android.R.anim.fade_in,
//                    android.R.anim.fade_out
//                )
//            }
//        }
    }

    class ViewHolder(private val binding: ItemMenuHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: MenuHomeEnum) {
            binding.menu = menu
        }
    }
}