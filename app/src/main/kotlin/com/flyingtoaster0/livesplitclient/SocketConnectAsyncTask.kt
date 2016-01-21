package com.flyingtoaster0.livesplitclient

import android.os.AsyncTask
import java.io.OutputStreamWriter
import java.net.ConnectException
import java.net.Socket

open class SocketConnectAsyncTask(val client: LiveSplitClient, val hostname: String, val port: Int) : AsyncTask<Void, Void, Socket>() {

    override fun doInBackground(vararg params: Void?): Socket? {
        return try {
            createSocket(hostname, port) ?: null
        } catch (e: ConnectException) {
            client.onConnectionFailure()
            null
        }
    }

    open fun createSocket(hostname: String, port: Int) : Socket? {
        return Socket(hostname, port)
    }

    override fun onPostExecute(socket: Socket) {
        val outputStreamWriter = OutputStreamWriter(socket.outputStream, "UTF-8")
        client.onConnectionSuccess(socket, outputStreamWriter);
    }
}