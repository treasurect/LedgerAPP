package com.treasure.ledger

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.treasure.basic.utils.ScreenUtils
import com.treasure.basic.utils.SpUtils
import com.treasure.ledger.data.db.UserRepository
import com.treasure.ledger.databinding.ActivitySplashBinding
import com.treasure.ledger.func.MainActivity
import com.treasure.ledger.func.login.LoginActivity
import com.treasure.ledger.utils.Constants
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
            val loginUid = SpUtils.getString(Constants.KEY_SP_LOGIN_UID)
            delay(3000)
            val userEntity = UserRepository.getInstance().getUserEntity(loginUid)
            userEntity?.let {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } ?: kotlin.run {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            this@SplashActivity.finish()
        }
    }
}