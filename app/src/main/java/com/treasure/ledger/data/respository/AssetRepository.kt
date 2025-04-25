package com.treasure.ledger.data.respository

import com.treasure.ledger.data.dao.AssetDao
import com.treasure.ledger.data.entity.AssetEntity

class AssetRepository(private val assetDao: AssetDao) {
    suspend fun getAllAssets() = assetDao.getAll()
    suspend fun getAssetsByType(type: String) = assetDao.getBySection(type)
    suspend fun insertAsset(asset: AssetEntity) = assetDao.insert(asset)
    suspend fun deleteAsset(asset: AssetEntity) = assetDao.delete(asset)
}
