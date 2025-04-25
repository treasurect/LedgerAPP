package com.treasure.ledger.func.summary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.treasure.ledger.BaseFragment
import com.treasure.ledger.databinding.FragmentSummaryBinding
import com.treasure.ledger.func.transactions.TransactionAdapter
import com.treasure.ledger.func.transactions.TransactionViewModel
import com.treasure.ledger.func.transactions.TransactionsCreateActivity

class SummaryFragment : BaseFragment() {
    private val viewModel: TransactionViewModel by viewModels()
    private lateinit var binding: FragmentSummaryBinding
    private var adapter: TransactionAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding.root
    }

    override fun lazyInit(view: View?) {
        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            adapter = TransactionAdapter(transactions)
            binding.rvTransactions.adapter = adapter
            binding.rvTransactions.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getRecentlyTransactionsList()

        binding.fabCreate.setOnClickListener {
            activity?.let {
                startActivity(Intent(activity, TransactionsCreateActivity::class.java))
            }
        }
    }
}