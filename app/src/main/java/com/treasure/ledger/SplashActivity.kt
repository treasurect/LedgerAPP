package com.treasure.ledger

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.treasure.ledger.databinding.ActivitySplashBinding
import com.treasure.basic.utils.ScreenUtils
import com.treasure.ledger.func.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        com.treasure.basic.utils.ScreenUtils.immersiveStatusBarDark(this)

        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }
    }
}