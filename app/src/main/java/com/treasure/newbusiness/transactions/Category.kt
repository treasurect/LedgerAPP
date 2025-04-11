package com.treasure.newbusiness.transactions

data class Category(
    val id: Int,
    val name: String,
    val iconRes: Int,
    val type: String // "支出" or "收入"
)
