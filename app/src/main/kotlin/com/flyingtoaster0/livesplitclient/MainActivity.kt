package com.flyingtoaster0.livesplitclient

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity(), ClientConnectionListener {

    val mClient = LiveSplitClient(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        var connectButton = findViewById(R.id.connect_button)
        connectButton.setOnClickListener {
            mClient.connect("192.168.1.172", 16834)
        }

        var splitButton = findViewById(R.id.split_button)
        splitButton.setOnClickListener {
            mClient.send(LiveSplitClient.START_OR_SPLIT)
        }

        var disconnectButton = findViewById(R.id.disconnect_button)
        disconnectButton.setOnClickListener {
            mClient.close()
        }
    }

    override fun onConnectionSuccess() {
//        throw UnsupportedOperationException("MainActivity Connection success")
    }

    override fun onConnectionFailure() {
        throw UnsupportedOperationException("MainActivity Connection failure")
    }
}
