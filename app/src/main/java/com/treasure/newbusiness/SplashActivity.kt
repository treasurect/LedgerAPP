package com.treasure.newbusiness

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.treasure.newbusiness.databinding.ActivitySplashBinding
import com.treasure.newbusiness.utils.ScreenUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ScreenUtils.immersiveStatusBarDark(this)

        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }
    }
}