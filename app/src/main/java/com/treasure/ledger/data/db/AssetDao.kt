package com.treasure.ledger.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.treasure.ledger.data.entity.AssetEntity

@Dao
interface AssetDao {
    @Query("SELECT * FROM assets")
    suspend fun getAll(): List<AssetEntity>
    @Query("SELECT * FROM assets WHERE uid = :uid")
    suspend fun getAll(uid: String): List<AssetEntity>

    @Query("SELECT * FROM assets WHERE uid = :uid AND section = :section")
    suspend fun getBySection(uid: String, section: String): List<AssetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: AssetEntity)

    @Delete
    suspend fun delete(asset: AssetEntity)
}

