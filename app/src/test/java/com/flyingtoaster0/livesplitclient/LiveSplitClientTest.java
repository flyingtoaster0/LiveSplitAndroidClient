package com.flyingtoaster0.livesplitclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class LiveSplitClientTest {

    private LiveSplitClient mClient;
    private ClientConnectionListener clientConnectionListener;

    @Before
    public void setup() throws IOException {
        clientConnectionListener = mock(ClientConnectionListener.class);
        mClient = new LiveSplitClient(clientConnectionListener);
        mClient.setSocket(mock(Socket.class));
        mClient.setOutputStreamWriter(mock(OutputStreamWriter.class));
    }

    @Test
    public void shouldSendCommands() throws IOException {
        mClient.send(LiveSplitClient.START_OR_SPLIT);
        verify(mClient.getOutputStreamWriter()).write(LiveSplitClient.START_OR_SPLIT, 0, LiveSplitClient.START_OR_SPLIT.length());
    }

    @Test
    public void shouldSendCommandsWithArgs() throws IOException {
        mClient.send(LiveSplitClient.SET_GAME_TIME, "10");
        verify(mClient.getOutputStreamWriter()).write(LiveSplitClient.SET_GAME_TIME + " 10", 0, LiveSplitClient.START_OR_SPLIT.length() + 2);
    }

    @Test
    public void shouldCloseSocket() throws IOException {
        mClient.close();
        verify(mClient.getSocket()).close();
    }

    @Test
    public void onSocketConnectionSuccess_shouldSetSocketAndStreamWriter() {
        Socket expectedSocket = mock(Socket.class);
        OutputStreamWriter expectedStreamWriter = mock(OutputStreamWriter.class);

        mClient.onConnectionSuccess(expectedSocket, expectedStreamWriter);

        Socket actualSocket = mClient.getSocket();
        OutputStreamWriter actualStreamWriter = mClient.getOutputStreamWriter();
        assertThat(actualSocket).isEqualTo(expectedSocket);
        assertThat(actualStreamWriter).isEqualTo(expectedStreamWriter);
    }

    @Test
    public void onSocketConnectionSuccess_shouldNotifyListener() {
        mClient.onConnectionSuccess(mock(Socket.class), mock(OutputStreamWriter.class));
        verify(clientConnectionListener).onConnectionSuccess();
    }

    @Test
    public void onSocketConnectionFailure_shouldNotifyListener() {
        mClient.onConnectionFailure();
        verify(clientConnectionListener).onConnectionFailure();
    }
}
