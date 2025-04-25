package com.treasure.ledger.func.assets

object AssetsUtils {
    fun transAssetSection(type: String): String {
        return when (type) {
            "bank" -> "银行卡"
            "payment" -> "第三方支付"
            "recharge" -> "虚拟资产"
            else -> "资产"
        }
    }
}