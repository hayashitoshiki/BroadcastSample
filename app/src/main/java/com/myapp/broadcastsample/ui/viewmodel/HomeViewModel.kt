package com.myapp.broadcastsample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _count: MutableLiveData<Int> = MutableLiveData(0)
    val  count: LiveData<Int> = _count
    private val _level: MutableLiveData<Int> = MutableLiveData(0)
    val level: LiveData<Int> = _level
    private val _temperature: MutableLiveData<Int> = MutableLiveData(0)
    val temperature: LiveData<Int> = _temperature
    private val _voltage: MutableLiveData<Int> = MutableLiveData(0)
    val voltage: LiveData<Int> = _voltage
    private val _health: MutableLiveData<Int> = MutableLiveData(0)
    val health: LiveData<Int> = _health
    private val _healthStr: MutableLiveData<String> = MutableLiveData("")
    val healthStr: LiveData<String> = _healthStr
    private val _plug: MutableLiveData<Int> = MutableLiveData(0)
    val plug: LiveData<Int> = _plug
    private val _plugStr: MutableLiveData<String> = MutableLiveData("")
    val plugStr: LiveData<String> = _plugStr
    private val _status : MutableLiveData<Int> = MutableLiveData(0)
    val status: LiveData<Int> = _status
    private val _statusStr: MutableLiveData<String> = MutableLiveData("")
    val statusStr: LiveData<String> = _statusStr

    fun setBatteryValue(
        level       : Int,
        temperature : Int,
        voltage     : Int ,
        health      : Int ,
        healthStr   : String ,
        plug        : Int,
        plugStr     : String,
        status      : Int,
        statusStr   : String = ""
    ) {
        Log.d("LocalBatteryBroadcastReceiver", "viewModel カウント = " + _count.value)
        _count.value = (_count.value ?: 0) + 1
        _level.value = level
        _temperature.value = temperature
        _voltage.value = voltage
        _health.value = health
        _healthStr.value = healthStr
        _plug.value = plug
        _plugStr.value = plugStr
        _status.value = status
        _statusStr.value = statusStr
    }
}