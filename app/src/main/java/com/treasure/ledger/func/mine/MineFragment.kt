package com.treasure.ledger.func.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.treasure.basic.utils.ToastUtils
import com.treasure.ledger.BaseFragment
import com.treasure.ledger.R
import com.treasure.ledger.databinding.FragmentMineBinding
import com.treasure.ledger.func.login.LoginActivity

class MineFragment : BaseFragment() {
    private lateinit var binding: FragmentMineBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        binding = FragmentMineBinding.inflate(inflater)
        return binding.root
    }

    override fun lazyInit(view: View?) {
        val mineMenuAdapter = MineMenuAdapter(getMenuItems())
        binding.recyclerMenu.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerMenu.adapter = mineMenuAdapter

        viewModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvUid.text = it?.uid?.toString() ?: "未登录"
            binding.tvDesc.text = "记账天数 ${it?.recordDays ?: 0} · 记账笔数 ${it?.recordCount ?: 0}"
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            activity?.let {
                startActivity(Intent(it,LoginActivity::class.java))
                ToastUtils.show(it, "已退出登录") }
        }
    }

    private fun getMenuItems(): List<MineMenuItem> {
        return listOf(
            MineMenuItem(R.drawable.ic_bill, "账单"),
            MineMenuItem(R.drawable.ic_category, "分类"),
            MineMenuItem(R.drawable.ic_assets, "资产"),
            MineMenuItem(R.drawable.ic_chart, "图表"),
            MineMenuItem(R.drawable.ic_backup, "备份"),
            MineMenuItem(R.drawable.ic_settings, "设置"),
            MineMenuItem(R.drawable.ic_feedback, "反馈"),
            MineMenuItem(R.drawable.ic_theme, "主题"),
            MineMenuItem(R.drawable.ic_about, "关于")
        )
    }

}