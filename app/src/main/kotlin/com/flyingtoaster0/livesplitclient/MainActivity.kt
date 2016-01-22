package com.flyingtoaster0.livesplitclient

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : Activity(), ClientConnectionListener {

    var client = LiveSplitClient(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        connectButton.setOnClickListener {
//            client.connect("localhost", 16834)
            onConnectionSuccess()
        }

        splitButton.setOnClickListener {
            client.send(LiveSplitClient.START_OR_SPLIT)
        }

        disconnectButton.setOnClickListener {
            client.close()
        }
    }

    override fun onConnectionSuccess() {
        Snackbar.make(splitButton, "Connected", Snackbar.LENGTH_SHORT)
        animateConnectComponentsDownOut()
    }

    override fun onConnectionFailure() {
        Snackbar.make(contentView, "Connection Failed", Snackbar.LENGTH_SHORT)
    }

    fun animateConnectComponentsDownOut() {
        val buttonDownOut = getDownOutAnimation(connectButton, contentView)
        val editTextDownOut = getDownOutAnimation(hostnameEditText, contentView)

        val downOutAnimatorSet = AnimatorSet()
        editTextDownOut.startDelay = 100
        downOutAnimatorSet.playTogether(buttonDownOut, editTextDownOut)
        downOutAnimatorSet.start()
    }

    fun animateSplitNameDown() {
        val animatorDown = ObjectAnimator.ofFloat(subtitleBar, "translationY", -subtitleBar.height.toFloat(), 0f)
        animatorDown.setDuration(250)
        subtitleBar.visibility = View.VISIBLE
        animatorDown.start()
    }
}
