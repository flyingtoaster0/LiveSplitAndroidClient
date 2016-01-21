package com.flyingtoaster0.livesplitclient

import java.io.OutputStreamWriter
import java.net.Socket

interface ClientConnectionListener {
    fun onConnectionSuccess();
    fun onConnectionFailure();
}