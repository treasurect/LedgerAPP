package com.treasure.ledger.func.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.treasure.basic.utils.SpUtils
import com.treasure.ledger.data.db.TransactionRepository
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.utils.Constants
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    val transactions: MutableLiveData<List<TransactionEntity>> = MutableLiveData()

    fun addTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        TransactionRepository.getInstance().insert(transaction)
    }

    fun removeTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        TransactionRepository.getInstance().delete(transaction)
    }

    fun getAllTransactionsList() = viewModelScope.launch {
        val uid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
        transactions.postValue(TransactionRepository.getInstance().getAllTransactions(uid))
    }

    fun getRecentlyTransactionsList() = viewModelScope.launch {
        val uid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
        transactions.postValue(TransactionRepository.getInstance().getRecentlyTransactions(uid))
    }
}
