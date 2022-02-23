package com.myapp.broadcastsample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.myapp.broadcastsample.ui.screen.HomeScreen
import com.myapp.broadcastsample.ui.theme.BroadcastSampleTheme
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.activity.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.myapp.broadcastsample.ui.util.BatteryBroadcastReceiver
import com.myapp.broadcastsample.ui.util.BatteryBroadcastReceiverParam
import com.myapp.broadcastsample.ui.util.LocalBatteryBroadcastReceiver
import com.myapp.broadcastsample.ui.viewmodel.HomeViewModel


class MainActivity : ComponentActivity() {

    private lateinit var localBatteryBroadcastReceiver: LocalBatteryBroadcastReceiver
    private lateinit var broadcastReceiver: BatteryBroadcastReceiver
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var localBroadcastManager : LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 各種初期化
        localBatteryBroadcastReceiver = LocalBatteryBroadcastReceiver(homeViewModel)
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        broadcastReceiver = BatteryBroadcastReceiver()
        setContent {
            BroadcastSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(homeViewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LocalBatteryBroadcastReceiver", "onStart")
        // 設定
        localBroadcastManager.registerReceiver(localBatteryBroadcastReceiver, IntentFilter(BatteryBroadcastReceiverParam.LOCAL_RECEIVER_ID))
        registerReceiver(broadcastReceiver,  IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onStop() {
        super.onStop()
        Log.d("LocalBatteryBroadcastReceiver", "onStop")
        // 解除（メモリリーク防止）
        unregisterReceiver(broadcastReceiver)
        localBroadcastManager.unregisterReceiver(broadcastReceiver)
    }
}
