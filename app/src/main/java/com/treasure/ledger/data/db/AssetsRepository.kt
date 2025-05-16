package com.treasure.ledger.data.db

import com.treasure.ledger.LedgerAPP
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.AssetEntity

class AssetsRepository {
    companion object {
        @Volatile
        private var INSTANCE: AssetsRepository? = null
        fun getInstance(): AssetsRepository {
            return INSTANCE?.let {
                it
            } ?: kotlin.run {
                INSTANCE = AssetsRepository()
                INSTANCE!!
            }
        }
    }
    private val assetsDao = AppDatabase.getInstance(LedgerAPP.context).assetsDao()
    
    suspend fun getAllAssets(uid: String) = assetsDao.getAll(uid)
    suspend fun getAssetsByType(uid: String, type: String) = assetsDao.getBySection(uid, type)
    suspend fun insertAsset(asset: AssetEntity) = assetsDao.insert(asset)
    suspend fun deleteAsset(asset: AssetEntity) = assetsDao.delete(asset)
}
