package com.treasure.ledger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    private var isViewCreated = false // 标志位，View是否已创建

    private var isDataLoaded = false // 标志位，数据是否已加载

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
    }

    abstract fun lazyInit(view: View?)

    override fun onResume() {
        super.onResume()
        if (isViewCreated && !isDataLoaded && view != null) {
            lazyInit(view)
            isDataLoaded = true
        }
    }
}