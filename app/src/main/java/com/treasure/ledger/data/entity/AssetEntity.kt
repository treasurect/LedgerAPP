package com.treasure.ledger.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class AssetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val section: String, // 栏目类型：bank->银行卡、payment->三方支付、recharge->充值卡
    val name: String,
    val amount: Double,
    val iconRes: Int
)

