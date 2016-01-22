package com.flyingtoaster0.livesplitclient;

import android.support.design.widget.Snackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@Config(constants = BuildConfig.class, shadows = ShadowSnackbar.class)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    private ActivityController<MainActivity> mController;
    private MainActivity mActivity;

    @Before
    public void setup() {
        mController = Robolectric.buildActivity(MainActivity.class);
        mActivity = spy(mController.create().start().resume().visible().get());
        mActivity.setClient(mock(LiveSplitClient.class));
    }

    @Test
    public void whenConnectionSucceeds_shouldShowSnackbar() {
        mActivity.onConnectionSuccess();

        Snackbar latestSnackbar = ShadowSnackbar.getLatestSnackbar();
        assertThat(latestSnackbar).isNotNull();
    }

    @Test
    public void whenConnectionFails_shouldShowSnackbar() {
        mActivity.onConnectionFailure();

        Snackbar latestSnackbar = ShadowSnackbar.getLatestSnackbar();
        assertThat(latestSnackbar).isNotNull();
    }
}
