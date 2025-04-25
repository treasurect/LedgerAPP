package com.treasure.ledger.func.assets

import com.chad.library.adapter.base.entity.MultiItemEntity

sealed class AssetItem : MultiItemEntity {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_CARD = 1
        const val TYPE_ADD_BUTTON = 2
    }

    data class Section(val title: String) : AssetItem() {
        override val itemType: Int
            get() = TYPE_HEADER
    }

    data class Card(val name: String, val amount: Double, val iconRes: Int) : AssetItem() {
        override val itemType: Int
            get() = TYPE_CARD
    }

    object AddButton : AssetItem() {
        override val itemType: Int
            get() = TYPE_ADD_BUTTON
    }
}

