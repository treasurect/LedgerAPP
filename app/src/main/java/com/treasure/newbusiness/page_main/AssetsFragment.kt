package com.treasure.newbusiness.page_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treasure.newbusiness.BaseFragment
import com.treasure.newbusiness.databinding.FragmentAssetsBinding

class AssetsFragment : BaseFragment() {
    private lateinit var binding: FragmentAssetsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}