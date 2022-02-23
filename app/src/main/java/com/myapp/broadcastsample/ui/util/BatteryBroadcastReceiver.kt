package com.myapp.broadcastsample.ui.util

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.myapp.broadcastsample.ui.viewmodel.HomeViewModel

/**
 * BatteryBroadcastReceiver用パラメータ
 */
object BatteryBroadcastReceiverParam {

    // ローカル伝達ようレシーバーID
    const val LOCAL_RECEIVER_ID = "LocalBatteryBroadcastReceiver"

    // 受け渡し用パラメータ
    const val LEVEL = "level"
    const val TEMPERATURE = "temperature"
    const val VOLTAGE = "voltage"
    const val HEALTH = "health"
    const val HEALTH_STR = "healthStr"
    const val PLUG = "plug"
    const val PLUG_STR = "plugStr"
    const val STATUS = "status"
    const val STATUS_STR = "statusStr"
}

/**
 * 外部からの受け取り用バッテリー監視用BroadcastReceiver
 *
 */
class BatteryBroadcastReceiver: BroadcastReceiver() {

    private var level       : Int    = 0
    private var temperature : Int    = 0
    private var voltage     : Int    = 0
    private var health      : Int    = 0
    private var healthStr   : String = ""
    private var plug        : Int    = 0
    private var plugStr     : String = ""
    private var status      : Int    = 0
    private var statusStr   : String = ""

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("BatteryBroadcastReceiver", "onReceive")
        if (intent == null) return

        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
        temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1)
        voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1)
        health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1)
        plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1)
        status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1)
        statusToString()
        context
            ?.let{ LocalBroadcastManager.getInstance(it.applicationContext) }
            ?.also{
                val sendIntent = Intent().apply{
                    action = BatteryBroadcastReceiverParam.LOCAL_RECEIVER_ID
                    putExtra(BatteryBroadcastReceiverParam.LEVEL, level)
                    putExtra(BatteryBroadcastReceiverParam.TEMPERATURE, temperature)
                    putExtra(BatteryBroadcastReceiverParam.VOLTAGE, voltage)
                    putExtra(BatteryBroadcastReceiverParam.HEALTH, health)
                    putExtra(BatteryBroadcastReceiverParam.HEALTH_STR, healthStr)
                    putExtra(BatteryBroadcastReceiverParam.PLUG, plug)
                    putExtra(BatteryBroadcastReceiverParam.PLUG_STR, plugStr)
                    putExtra(BatteryBroadcastReceiverParam.STATUS, status)
                    putExtra(BatteryBroadcastReceiverParam.STATUS_STR, statusStr)
                }
              it.sendBroadcast(sendIntent)
            }
    }

    private fun statusToString(){
        healthStr = when(health) {
            BatteryManager.BATTERY_HEALTH_GOOD                -> "正常"
            BatteryManager.BATTERY_HEALTH_COLD                -> "低音異常"
            BatteryManager.BATTERY_HEALTH_DEAD                -> "故障"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE        -> "電圧異常"
            BatteryManager.BATTERY_HEALTH_OVERHEAT            -> "温度異常"
            BatteryManager.BATTERY_HEALTH_UNKNOWN             -> "状態不明"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "未知の異常"
            else -> "不明"
        }
        plugStr = when(plug){
            BatteryManager.BATTERY_PLUGGED_AC       -> "AC接続"
            BatteryManager.BATTERY_PLUGGED_USB      -> "USB接続"
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> "無線接続"
            else -> "未接続"
        }
        statusStr = when(status){
            BatteryManager.BATTERY_STATUS_CHARGING     -> "充電中"
            BatteryManager.BATTERY_STATUS_FULL         -> "充電完了"
            BatteryManager.BATTERY_STATUS_DISCHARGING  -> "消費中"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "未充電"
            BatteryManager.BATTERY_STATUS_UNKNOWN      -> "不明"
            else -> "不明"
        }
    }
}

/**
 * 内部への伝達用バッテリー監視用BroadcastReceiver
 *
 * @property homeViewModel
 */
class LocalBatteryBroadcastReceiver(private val homeViewModel: HomeViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("LocalBatteryBroadcastReceiver", "onReceive")
        if (intent == null) return
        val level = intent.getIntExtra(BatteryBroadcastReceiverParam.LEVEL, 0)
        val temperature = intent.getIntExtra(BatteryBroadcastReceiverParam.TEMPERATURE, 0)
        val voltage = intent.getIntExtra(BatteryBroadcastReceiverParam.VOLTAGE, 0)
        val health = intent.getIntExtra(BatteryBroadcastReceiverParam.HEALTH, 0)
        val healthStr = intent.getStringExtra(BatteryBroadcastReceiverParam.HEALTH_STR) ?: ""
        val plug = intent.getIntExtra(BatteryBroadcastReceiverParam.PLUG, 0)
        val plugStr = intent.getStringExtra(BatteryBroadcastReceiverParam.PLUG_STR) ?: ""
        val status = intent.getIntExtra(BatteryBroadcastReceiverParam. STATUS, 0)
        val statusStr = intent.getStringExtra(BatteryBroadcastReceiverParam.STATUS_STR) ?: ""
        homeViewModel.setBatteryValue(
            level ,
            temperature,
            voltage,
            health,
            healthStr,
            plug,
            plugStr,
            status,
            statusStr,
        )
    }
}