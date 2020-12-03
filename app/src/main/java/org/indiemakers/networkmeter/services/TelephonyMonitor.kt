package org.indiemakers.networkmeter.services

import org.indiemakers.networkmeter.models.Network
import org.indiemakers.networkmeter.models.NetworkType.GSM
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelephonyMonitor @Inject constructor() {

    fun getNetworks():List<Network> {
        val networks = mutableListOf<Network>()
        networks.add(Network("test-mobile", -100.5f, 0, GSM))
        networks.add(Network("test-mobile", -100.5f, 0, GSM))
        return networks
    }
}