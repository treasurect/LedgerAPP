package com.treasure.ledger.func.assets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.treasure.basic.utils.SpUtils
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.AssetEntity
import com.treasure.ledger.data.db.AssetsRepository
import com.treasure.ledger.utils.Constants
import kotlinx.coroutines.launch

class AssetsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    // 分组展示的结构：节标题 + 内容项
    val assetListLiveData = MutableLiveData<MutableList<AssetItem>>()

    init {

    }

    // 加载数据库中所有数据，并分组整理
    fun refreshAssets() {
        viewModelScope.launch {
            val all = AssetsRepository.getInstance().getAllAssets(SpUtils.getString(Constants.KEY_SP_LOGIN_UID))
            val grouped = all.groupBy { it.section }
            val result = mutableListOf<AssetItem>()
            grouped.forEach { (section, assets) ->
                result.add(AssetItem.Section(section))
                assets.forEach {
                    result.add(AssetItem.Card(it.name, it.amount, it.iconRes))
                }
            }
            assetListLiveData.postValue(result)
        }
    }

    // 插入新资产并刷新
    fun insertAsset(section: String, name: String, amount: Double, iconRes: Int) {
        val uid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
        insertAsset(AssetEntity(uid = uid, section = section, name = name, amount = amount, iconRes = iconRes))
    }

    fun insertAsset(assetEntity: AssetEntity) {
        viewModelScope.launch {
            AssetsRepository.getInstance().insertAsset(assetEntity)
            refreshAssets()
        }
    }
}


