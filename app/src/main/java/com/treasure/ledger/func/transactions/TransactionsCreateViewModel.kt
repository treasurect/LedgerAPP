package com.treasure.ledger.func.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.treasure.ledger.data.AppDatabase
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.data.db.TransactionRepository
import kotlinx.coroutines.launch

class TransactionsCreateViewModel(application: Application) : AndroidViewModel(application) {

    fun addTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        TransactionRepository.getInstance().insert(transaction)
    }
}