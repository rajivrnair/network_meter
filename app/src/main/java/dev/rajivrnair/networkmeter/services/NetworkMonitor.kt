package dev.rajivrnair.networkmeter.services

import android.content.Context
import dev.rajivrnair.networkmeter.models.Network
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitor @Inject constructor() {

    @Inject lateinit var telephonyMonitor: TelephonyMonitor
    @Inject lateinit var wifiMonitor: WifiMonitor

    fun getNetworks(showWifi: Boolean, showGsm: Boolean, applicationContext: Context):List<Network> {
        val networks = mutableListOf<Network>()

        if(showWifi) networks.addAll(wifiMonitor.getNetworks(applicationContext))
        if(showGsm) networks.addAll(telephonyMonitor.getNetworks(applicationContext))

        return networks
    }
}