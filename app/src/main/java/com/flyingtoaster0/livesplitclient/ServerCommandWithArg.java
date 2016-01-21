package com.flyingtoaster0.livesplitclient;

import android.support.annotation.StringDef;

@StringDef({LiveSplitClient.SET_GAME_TIME, LiveSplitClient.SET_LOADING_TIMES, LiveSplitClient.SET_COMPARISON, LiveSplitClient.GET_DELTA, LiveSplitClient.GET_FINAL_TIME, LiveSplitClient.GET_PREDICTED_TIME})
public @interface ServerCommandWithArg {}