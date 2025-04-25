package com.treasure.ledger.func.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.treasure.ledger.R

class CategoryAdapter(
    private val categories: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedId: Int? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.findViewById<LinearLayout>(R.id.category_item_root)
        val icon = view.findViewById<ImageView>(R.id.iv_icon)
        val name = view.findViewById<TextView>(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.name.text = category.name
        holder.icon.setImageResource(category.iconRes)

        val isSelected = category.id == selectedId
        holder.root.background = ContextCompat.getDrawable(
            holder.itemView.context,
            if (isSelected) R.drawable.bg_category_selected else R.drawable.bg_category_unselected
        )

        holder.root.setOnClickListener {
            selectedId = category.id
            notifyDataSetChanged()
            onItemClick(category)
        }
    }
}
