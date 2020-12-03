package org.indiemakers.networkmeter.services

import org.indiemakers.networkmeter.models.Network
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitor @Inject constructor() {

    @Inject lateinit var telephonyMonitor: TelephonyMonitor
    @Inject lateinit var wifiMonitor: WifiMonitor

    fun getNetworks(showWifi: Boolean, showGsm: Boolean):List<Network> {
        val networks = mutableListOf<Network>()

        if(showWifi) networks.addAll(wifiMonitor.getNetworks())
        if(showGsm) networks.addAll(telephonyMonitor.getNetworks())

        return networks
    }
}