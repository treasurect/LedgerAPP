package com.treasure.ledger.func.assets

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.treasure.basic.utils.ToastUtils
import com.treasure.ledger.R

class AssetsAdapter : BaseMultiItemQuickAdapter<AssetItem, BaseViewHolder>() {

    init {
        addItemType(AssetItem.TYPE_HEADER, R.layout.item_asset_header)
        addItemType(AssetItem.TYPE_CARD, R.layout.item_asset_card)
        addItemType(AssetItem.TYPE_ADD_BUTTON, R.layout.item_asset_add)
    }

    override fun convert(holder: BaseViewHolder, item: AssetItem) {
        when (item) {
            is AssetItem.Section -> {
                holder.setText(R.id.tv_title, AssetsUtils.transAssetSection(item.title))
            }

            is AssetItem.Card -> {
                holder.setText(R.id.tv_asset_name, item.name)
                holder.setText(R.id.tv_asset_amount, "¥ %.2f".format(item.amount))
                holder.setImageResource(R.id.iv_asset_icon, item.iconRes)
            }

            is AssetItem.AddButton -> {
                holder.setText(R.id.tv_add, "+ 添加选项")
                holder.itemView.setOnClickListener {
                    ToastUtils.show(holder.itemView.context, "点击添加选项")
                    // 可以弹出添加弹窗
                }
            }
        }
    }
}

