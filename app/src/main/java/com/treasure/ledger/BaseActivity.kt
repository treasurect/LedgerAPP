package com.treasure.ledger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.treasure.basic.utils.ScreenUtils

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenUtils.immersiveStatusBarBase(this, true)
    }
}