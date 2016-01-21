package com.flyingtoaster0.livesplitclient

import java.io.OutputStreamWriter
import java.net.Socket

interface SocketConnectionListener {
    fun onConnectionSuccess(socket: Socket, outputStreamWriter: OutputStreamWriter);
    fun onConnectionFailure();
}