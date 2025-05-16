package com.treasure.ledger

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.treasure.basic.utils.LogHelper
import com.treasure.basic.utils.SpUtils

class LedgerAPP : Application() {

    companion object {
        private const val TAG = "LedgerAPP"

        // 获取 Application 实例
        lateinit var context: LedgerAPP
            private set

        // 获取 Activity 栈
        val activityStack = mutableListOf<Activity>()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initLibs()

        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }


    private fun initLibs() {
        SpUtils.init(this)
        // 初始化其他第三方库
        // Example: Retrofit, Room, Firebase, etc.
        LogHelper.logD("Third-party libraries initialized")
    }

    /**
     * Activity 生命周期回调
     */
    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activityStack.add(activity)
            LogHelper.logD("Activity created: ${activity.javaClass.simpleName}")
            LogHelper.logD("Current activity stack size: ${activityStack.size}")
        }

        override fun onActivityStarted(activity: Activity) {
            LogHelper.logD("Activity started: ${activity.javaClass.simpleName}")
        }

        override fun onActivityResumed(activity: Activity) {
            LogHelper.logD("Activity resumed: ${activity.javaClass.simpleName}")
        }

        override fun onActivityPaused(activity: Activity) {
            LogHelper.logD("Activity paused: ${activity.javaClass.simpleName}")
        }

        override fun onActivityStopped(activity: Activity) {
            LogHelper.logD("Activity stopped: ${activity.javaClass.simpleName}")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            LogHelper.logD("Activity save instance state: ${activity.javaClass.simpleName}")
        }

        override fun onActivityDestroyed(activity: Activity) {
            activityStack.remove(activity)
            LogHelper.logD("Activity destroyed: ${activity.javaClass.simpleName}")
            LogHelper.logD("Current activity stack size: ${activityStack.size}")
        }
    }

    /**
     * 获取当前 Activity
     */
    fun getCurrentActivity(): Activity? {
        return if (activityStack.isNotEmpty()) activityStack.last() else null
    }

    /**
     * 退出应用程序
     */
    fun exitApp() {
        activityStack.forEach { activity ->
            activity.finish()
        }
        activityStack.clear()
        // 可以在这里添加其他清理逻辑
    }

    /**
     * 重启应用程序
     */
    fun restartApp() {
        // 获取启动 Activity
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        exitApp()
        startActivity(intent)
    }
}