package com.flyingtoaster0.livesplitclient;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SocketConnectAsyncTaskTest {

    private LiveSplitClient mClient;
    private SocketConnectAsyncTask mTask;

    @Before
    public void setup() {
        mClient = spy(new LiveSplitClient(mock(ClientConnectionListener.class)));
    }

    @Test
    public void doInBackground_shouldReturnSocket() {
        mTask = new StubbedSocketConnectAsyncTask(mClient, "localhost", 16834);
        Socket actualSocket = mTask.doInBackground();

        assertThat(actualSocket).isNotNull();
    }

    @Test
    public void whenConnectionFails_shouldReturnNullSocket() {
        mTask = new SocketConnectAsyncTask(mClient, "localhost", 16834);
        Socket actualSocket = mTask.doInBackground();

        assertThat(actualSocket).isNull();
    }

    @Test
    public void whenConnectionFails_shouldNotifyClient() {
        mTask = new SocketConnectAsyncTask(mClient, "localhost", 16834);
        mTask.doInBackground();

        verify(mTask.getClient()).onConnectionFailure();
    }

    @Test
    public void onPostExecute_shouldPassSocketAndWriterToClient() throws IOException {
        mTask = spy(new StubbedSocketConnectAsyncTask(mClient, "localhost", 16834));
        Socket expectedSocket = mock(Socket.class);
        OutputStream outputStream = mock(OutputStream.class);
        when(expectedSocket.getOutputStream()).thenReturn(outputStream);

        mTask.onPostExecute(expectedSocket);

        verify(mTask.getClient()).onConnectionSuccess(eq(expectedSocket), any(OutputStreamWriter.class));
    }

    // Stubbed here instead of with mockito due to compatibility issues between Kotlin and mockito
    class StubbedSocketConnectAsyncTask extends SocketConnectAsyncTask {
        public StubbedSocketConnectAsyncTask(@NotNull LiveSplitClient client, @NotNull String hostname, int port) {
            super(client, hostname, port);
        }

        @Override
        public Socket createSocket(String hostname, int port) {
            return mock(Socket.class);
        }
    }
}
