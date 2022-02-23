package com.myapp.broadcastsample.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.broadcastsample.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val count = homeViewModel.count.observeAsState(0)
    Column {
        Text(text = "System BroadcastReceiver")
        Text(
            text = "更新回数 = " + count.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの残量 = " + homeViewModel.level.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの温度 = " + homeViewModel.temperature.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリー電圧 = " + homeViewModel.voltage.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの健康状態 = " + homeViewModel.health.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの健康状態 = " + homeViewModel.healthStr.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "ケーブルの接続状態 = " + homeViewModel.plug.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "ケーブルの接続状態 = " + homeViewModel.plugStr.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの充電状態 = " + homeViewModel.status.value,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "バッテリーの充電状態 = " + homeViewModel.statusStr.value,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}