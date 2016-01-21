package com.flyingtoaster0.livesplitclient

import java.io.OutputStreamWriter
import java.net.Socket

class LiveSplitClient {

    companion object {
        const val START_TIMER = "starttimer\r\n"
        const val START_OR_SPLIT = "startorsplit\r\n"
        const val SPLIT = "split\r\n"
        const val UNSPLIT = "unsplit\r\n"
        const val SKIP_SPLIT = "skipsplit\r\n"
        const val PAUSE = "pause\r\n"
        const val RESUME = "resume\r\n"
        const val RESET = "reset\r\n"
        const val INIT_GAME_TIME = "initgametime\r\n"
        const val SET_GAME_TIME = "setgametime\r\n"
        const val SET_LOADING_TIMES = "setloadingtimes\r\n"
        const val PAUSE_GAME_TIME = "pausegametime\r\n"
        const val UNPAUSE_GAME_TIME = "unpausegametime\r\n"
        const val SET_COMPARISON = "setcomparison\r\n"
        const val GET_DELTA = "getdelta\r\n"
        const val GET_LAST_SPLIT_TIME = "getlastsplittime\r\n"
        const val GET_COMPARISON_SPLIT_TIME = "getcomparisonsplittime\r\n"
        const val GET_CURRENT_TIME = "getcurrenttime\r\n"
        const val GET_FINAL_TIME = "getfinaltime\r\n"
        const val GET_PREDICTED_TIME = "getpredictedtime\r\n"
        const val GET_BEST_POSSIBLE_TIME = "getbestpossibletime\r\n"
        const val GET_SPLIT_INDEX = "getsplitindex\r\n"
        const val GET_CURRENT_SPLIT_NAME = "getcurrentsplitname\r\n"
        const val GET_PREVIOUS_SPLIT_NAME = "getprevioussplitname\r\n"
    }

    protected var socket: Socket? = null;
    protected var outputStreamWriter: OutputStreamWriter? = null;

    fun connect(hostname: String, portNumber: Int) {
        var isClosed : Boolean? = false;
        isClosed = socket?.isClosed;
        if (isClosed != null && isClosed) {
            socket = createSocket(hostname, portNumber)
            outputStreamWriter = OutputStreamWriter(socket?.outputStream, "UTF-8")
        }
    }

    fun createSocket(hostname: String, portNumber: Int) : Socket? {
        return Socket(hostname, portNumber)
    }

    fun send(@ServerCommand command: String) {
        outputStreamWriter?.write(command, 0, command.length)
        outputStreamWriter?.flush()
    }

    fun send(@ServerCommandWithArg command: String, commandArg: String) {
        val message = command + " " + commandArg
        outputStreamWriter?.write(message, 0, message.length)
        outputStreamWriter?.flush()

    }

    fun close() {
        socket?.close()
    }
}