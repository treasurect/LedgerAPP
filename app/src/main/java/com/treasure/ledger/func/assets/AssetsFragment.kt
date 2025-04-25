package com.treasure.ledger.func.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.treasure.ledger.BaseFragment
import com.treasure.ledger.databinding.FragmentAssetsBinding

class AssetsFragment : BaseFragment() {
    private lateinit var binding: FragmentAssetsBinding
    private val viewModel: AssetsViewModel by viewModels()
    private lateinit var adapter :AssetsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        binding = FragmentAssetsBinding.inflate(inflater)
        return binding.root
    }

    override fun lazyInit(view: View?) {
        binding.rvAssets.apply {
            layoutManager = LinearLayoutManager(activity)
            this@AssetsFragment.adapter = AssetsAdapter()
            adapter = this@AssetsFragment.adapter
        }

        viewModel.assetListLiveData.observe(viewLifecycleOwner) {
            adapter.setNewInstance(it)
        }
        viewModel.refreshAssets()

        binding.btnAddRecord.setOnClickListener {
            AssetInsertDialogFragment { asset ->
                viewModel.insertAsset(asset)
            }.show(parentFragmentManager, "AddAssetDialog")
        }

    }

}