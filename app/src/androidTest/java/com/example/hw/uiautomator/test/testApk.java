package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class testApk {

    private UiDevice device;
    private int width;
    private int height;
    public Context context;
    private Instrumentation instrumentation;
    public String pkgsys = "com.google.android.dialer";

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
    }

    @Test
    public void testLogin() throws UiObjectNotFoundException {
        width = device.getDisplayWidth();
        height = device.getDisplayHeight();

        device.pressHome();

        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        testUtil.performClickByResIdandInstance("com.google.android.dialer:id/icon", 2);
    }

}
