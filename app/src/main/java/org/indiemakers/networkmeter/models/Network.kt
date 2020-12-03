package org.indiemakers.networkmeter.models

data class Network(val name: String, val signalStrength: Float, val level: Int, val type: NetworkType)