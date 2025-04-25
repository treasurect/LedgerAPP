package com.treasure.ledger.func.mine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.treasure.ledger.databinding.ItemMenuBinding

class MineMenuAdapter(private val items: List<MineMenuItem>) :
    RecyclerView.Adapter<MineMenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivIcon.setImageResource(item.iconRes)
        holder.binding.tvLabel.text = item.label
    }

    override fun getItemCount(): Int = items.size
}
