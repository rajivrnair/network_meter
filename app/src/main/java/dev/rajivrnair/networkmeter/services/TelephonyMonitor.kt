package dev.rajivrnair.networkmeter.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.*
import androidx.core.app.ActivityCompat
import dev.rajivrnair.networkmeter.models.Network
import dev.rajivrnair.networkmeter.models.NetworkType
import dev.rajivrnair.networkmeter.models.NetworkType.GSM
import dev.rajivrnair.networkmeter.models.NetworkType.LTE
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TelephonyMonitor @Inject constructor() {

    fun getNetworks(context: Context):List<Network> {
        val networks = mutableListOf<Network>()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return networks
        }

        val telephonyManager = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val cellInfo = telephonyManager.allCellInfo
        for(info in cellInfo) {
            println("cellinfo: $info")
            if(!info.isRegistered) continue

            var signalStrength = 0
            var signalLevel = 0
            if(info is CellInfoLte) {
                val lte = info as CellInfoLte
                signalStrength = lte.cellSignalStrength.dbm
                signalLevel = calculateLTESignalLevel(signalStrength)

            } else if (info is CellInfoGsm) {
                val gsm = info as CellInfoGsm
                signalStrength = gsm.cellSignalStrength.dbm
                signalLevel = calculateGSMSignalLevel(signalStrength)
            }


            networks.add(Network(telephonyManager.networkOperatorName, "${signalStrength}dBm", signalLevel,
                getType(info)
            ))
        }

        return networks
    }

    private fun getType(info: CellInfo): NetworkType {
        return if(info is CellInfoLte) LTE else GSM
    }

    private fun calculateGSMSignalLevel(level: Int): Int {
        if(level >= -70) return 4
        if(level > -85) return 3
        if(level > -100) return 2
        if(level > -110) return 1
        return 0
    }

    private fun calculateLTESignalLevel(level: Int): Int {
        if(level >= -90) return 4
        if(level > -105) return 3
        if(level > -110) return 2
        if(level > -120) return 1
        return 0
    }
}