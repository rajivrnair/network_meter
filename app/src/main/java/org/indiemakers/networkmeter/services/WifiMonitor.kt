package org.indiemakers.networkmeter.services

import org.indiemakers.networkmeter.models.Network
import org.indiemakers.networkmeter.models.NetworkType.WIFI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiMonitor @Inject constructor() {

    fun getNetworks():List<Network> {
        val networks = mutableListOf<Network>()
        networks.add(Network("test-wifi", -50.5f, 2, WIFI))
        networks.add(Network("test-wifi", -70.0f, 3, WIFI))
        networks.add(Network("test-wifi", -85.5f, 4, WIFI))
        return networks
    }
}