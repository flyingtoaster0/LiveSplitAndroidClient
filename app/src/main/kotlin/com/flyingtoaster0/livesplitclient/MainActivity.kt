package com.flyingtoaster0.livesplitclient

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    val mClient = LiveSplitClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        var connectButton = findViewById(R.id.connect_button)
        connectButton.setOnClickListener {
            mClient.connect("localhost", 16834)
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
}
