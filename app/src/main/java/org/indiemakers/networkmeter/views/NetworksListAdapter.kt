package org.indiemakers.networkmeter.views

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.indiemakers.networkmeter.R
import org.indiemakers.networkmeter.models.Network
import org.indiemakers.networkmeter.models.NetworkType
import org.indiemakers.networkmeter.models.NetworkType.GSM
import org.indiemakers.networkmeter.models.NetworkType.WIFI

class NetworksListAdapter(private var dataSet: List<Network>) :
    RecyclerView.Adapter<NetworkViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NetworkViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.network_item, viewGroup, false)
        return NetworkViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NetworkViewHolder, position: Int) {
        val network = dataSet[position]
        viewHolder.name.text = network.name
        viewHolder.type.setImageDrawable(getNetworkType(network.type, viewHolder))
        viewHolder.strength.text = network.signalStrength.toString()

        viewHolder.quality.setImageDrawable(getNetworkQualityImage(network.level, viewHolder))
        viewHolder.quality.setColorFilter(getNetworkQualityColour(network.level, viewHolder))
    }

    private fun getNetworkQualityColour(level: Int, viewHolder: NetworkViewHolder): Int {
        return when(level) {
            0 -> viewHolder.colorCrap
            1 -> viewHolder.colorPoor
            2 -> viewHolder.colorFair
            3 -> viewHolder.colorGood
            4 -> viewHolder.colorExcellent
            else -> viewHolder.colorCrap
        }
    }

    private fun getNetworkQualityImage(level: Int, viewHolder: NetworkViewHolder): Drawable? {
        return when(level) {
            0 -> viewHolder.imgCrap
            1 -> viewHolder.imgPoor
            2 -> viewHolder.imgFair
            3 -> viewHolder.imgGood
            4 -> viewHolder.imgExcellent
            else -> viewHolder.imgCrap
        }
    }

    private fun getNetworkType(type: NetworkType, viewHolder: NetworkViewHolder): Drawable? {
        return when(type) {
            WIFI -> viewHolder.typeWifi
            GSM -> viewHolder.typeGsm
        }
    }

    fun updateList(list: List<Network>) {
        dataSet = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size
}
