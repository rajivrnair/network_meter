package dev.rajivrnair.networkmeter.services

import android.content.Context
import android.net.wifi.WifiManager
import dev.rajivrnair.networkmeter.models.Network
import dev.rajivrnair.networkmeter.models.NetworkType.WIFI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiMonitor @Inject constructor() {

    fun getNetworks(context: Context):List<Network> {
        val networks = mutableListOf<Network>()

        val wifiManager: WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiList = wifiManager.scanResults
        for(scan in wifiList) {
            val level = calculateSignalLevel(scan.level)
            val name = if(scan.SSID.isBlank()) "<Blank>" else scan.SSID  ?: "Hidden"
            networks.add(Network(name, "${scan.level}dBm", level, WIFI))
        }
        return networks
    }

    private fun calculateSignalLevel(level: Int): Int {
        if(level >= -50) return 4
        if(level >= -60) return 3
        if(level >= -70) return 2
        if(level >= -100) return 1
        return 0
    }
}