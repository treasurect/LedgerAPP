package com.treasure.newbusiness

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.treasure.newbusiness.databinding.ActivityMainBinding
import com.treasure.newbusiness.page_main.AssetsFragment
import com.treasure.newbusiness.page_main.MineFragment
import com.treasure.newbusiness.page_main.TransactionsFragment
import com.treasure.newbusiness.page_main.SummaryFragment


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val summaryFragment = SummaryFragment()
    private val transactionsFragment = TransactionsFragment()
    private val assetsFragment = AssetsFragment()
    private val mineFragment = MineFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_summary -> summaryFragment.showFragment()
                R.id.nav_transactions -> transactionsFragment.showFragment()
                R.id.nav_assets -> assetsFragment.showFragment()
                R.id.nav_mine -> mineFragment.showFragment()
            }
            true
        }
        binding.bottomNav.selectedItemId = R.id.nav_summary
    }

    private fun Fragment.showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, this)
            .commit()
    }
}

