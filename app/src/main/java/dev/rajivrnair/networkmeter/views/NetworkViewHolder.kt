package dev.rajivrnair.networkmeter.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.rajivrnair.networkmeter.R

class NetworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val type: ImageView = view.findViewById(R.id.network_type)
    val name: TextView = view.findViewById(R.id.network_name)
    val strength: TextView = view.findViewById(R.id.signal_strength)
    val quality: ImageView = view.findViewById(R.id.signal_quality)

    private val context: Context = view.context
    val colorExcellent: Int = ContextCompat.getColor(context, R.color.signal_excellent)
    val colorGood: Int = ContextCompat.getColor(context, R.color.signal_good)
    val colorFair: Int = ContextCompat.getColor(context, R.color.signal_fair)
    val colorPoor: Int = ContextCompat.getColor(context, R.color.signal_poor)
    val colorCrap: Int = ContextCompat.getColor(context, R.color.signal_crap)

    val imgExcellent: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_sentiment_very_satisfied_black_24)
    val imgGood: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_sentiment_very_satisfied_black_24)
    val imgFair: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_sentiment_very_dissatisfied_black_24)
    val imgPoor: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_sentiment_very_dissatisfied_black_24)
    val imgCrap: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_sick_black_24)

    val typeWifi: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_wifi_black_24)
    val typeMobile: Drawable? = ContextCompat.getDrawable(context, R.drawable.baseline_signal_cellular_alt_black_24)
}