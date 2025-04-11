package com.treasure.newbusiness.page_main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.treasure.newbusiness.BaseFragment
import com.treasure.newbusiness.databinding.FragmentSummaryBinding
import com.treasure.newbusiness.transactions.TransactionsCreateActivity

class SummaryFragment : BaseFragment() {
    private val viewModel: TransactionViewModel by viewModels()
    private lateinit var binding: FragmentSummaryBinding
    private var adapter: TransactionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabCreate.setOnClickListener {
            activity?.let {
                startActivity(Intent(activity, TransactionsCreateActivity::class.java))
            }
        }

        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            adapter = TransactionAdapter(transactions)
            binding.rvTransactions.adapter = adapter
            binding.rvTransactions.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getRecentlyTransactionsList()
    }
}