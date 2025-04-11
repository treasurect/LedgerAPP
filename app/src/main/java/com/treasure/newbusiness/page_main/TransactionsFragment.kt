package com.treasure.newbusiness.page_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.treasure.newbusiness.BaseFragment
import com.treasure.newbusiness.databinding.FragmentTransactionsBinding

class TransactionsFragment : BaseFragment() {
    private val viewModel: TransactionViewModel by viewModels()
    private lateinit var binding: FragmentTransactionsBinding
    private var adapter: TransactionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            adapter = TransactionAdapter(transactions)
            binding.rvTransactions.adapter = adapter
            binding.rvTransactions.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getAllTransactionsList()
    }
}