package com.flyingtoaster0.livesplitclient;

import android.support.annotation.StringDef;

@StringDef({LiveSplitClient.START_TIMER, LiveSplitClient.START_OR_SPLIT, LiveSplitClient.SPLIT, LiveSplitClient.UNSPLIT, LiveSplitClient.SKIP_SPLIT, LiveSplitClient.PAUSE, LiveSplitClient.RESUME, LiveSplitClient.RESET, LiveSplitClient.INIT_GAME_TIME, LiveSplitClient.SET_GAME_TIME, LiveSplitClient.PAUSE_GAME_TIME, LiveSplitClient.UNPAUSE_GAME_TIME, LiveSplitClient.GET_LAST_SPLIT_TIME, LiveSplitClient.GET_COMPARISON_SPLIT_TIME, LiveSplitClient.GET_CURRENT_TIME, LiveSplitClient.GET_SPLIT_INDEX, LiveSplitClient.GET_FINAL_TIME, LiveSplitClient.GET_BEST_POSSIBLE_TIME, LiveSplitClient.GET_CURRENT_SPLIT_NAME, LiveSplitClient.GET_PREVIOUS_SPLIT_NAME})
public @interface ServerCommand {}