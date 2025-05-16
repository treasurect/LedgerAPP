package com.treasure.ledger.func.transactions

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.treasure.basic.utils.SpUtils
import com.treasure.ledger.BaseActivity
import com.treasure.ledger.R
import com.treasure.ledger.data.entity.TransactionEntity
import com.treasure.ledger.databinding.ActivityTransactionsCreateBinding
import com.treasure.ledger.utils.Constants

class TransactionsCreateActivity : BaseActivity() {
    private val viewModel: TransactionsCreateViewModel by viewModels()
    private lateinit var binding: ActivityTransactionsCreateBinding
    private lateinit var adapter: CategoryAdapter
    private var selectedCategory: Category? = null
    private var currentType = "支出"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        updateCategoryList(currentType)
        binding.tvDate.text = com.treasure.basic.utils.DateUtils.getTodayDate()
        binding.tvTime.text = com.treasure.basic.utils.DateUtils.getNowSimpleTime()

        binding.btnSave.setOnClickListener {
            val amount = binding.etAmount.text.toString().trim()
            val remark = binding.etRemark.text.toString().trim()
            if (selectedCategory == null || amount.isEmpty()) {
                return@setOnClickListener
            }
            val uid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
            TransactionEntity(uid = uid, amount = amount.toDoubleOrNull() ?: 0.0, type = selectedCategory?.type?:"", category = selectedCategory?.name?:"", remark = remark).let {
                viewModel.addTransaction(it)
            }
            com.treasure.basic.utils.ToastUtils.show(this,"记账成功")
            this.finish()
        }
    }

    private fun setupTabs() {
        binding.tabType.addTab(binding.tabType.newTab().setText("支出"))
        binding.tabType.addTab(binding.tabType.newTab().setText("收入"))

        binding.tabType.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentType = if (tab?.position == 0) "支出" else "收入"
                updateCategoryList(currentType)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun updateCategoryList(type: String) {
        binding.rvCategory.layoutManager = GridLayoutManager(this, 4)
        val categories = getCategoryListByType(type)
        adapter = CategoryAdapter(categories) {
            selectedCategory = it
        }
        binding.rvCategory.adapter = adapter
    }

    private fun getCategoryListByType(type: String): List<Category> {
        return if (type == "支出") {
            listOf(
                Category(1, "餐饮", R.drawable.ic_food, "支出"),
                Category(2, "交通", R.drawable.ic_transport, "支出"),
                Category(3, "购物", R.drawable.ic_shopping, "支出"),
                Category(4, "娱乐", R.drawable.ic_game, "支出")
            )
        } else {
            listOf(
                Category(5, "工资", R.drawable.ic_salary, "收入"),
                Category(6, "理财", R.drawable.ic_invest, "收入"),
                Category(7, "兼职", R.drawable.ic_part_time, "收入")
            )
        }
    }
}