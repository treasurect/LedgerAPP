package com.treasure.ledger.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.treasure.ledger.data.dao.AssetDao
import com.treasure.ledger.data.dao.TransactionDao
import com.treasure.ledger.data.dao.UserDao
import com.treasure.ledger.data.entity.AssetEntity
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.data.entity.UserEntity

@Database(entities = [TransactionEntity::class, AssetEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun assetDao(): AssetDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nb_ledger_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
