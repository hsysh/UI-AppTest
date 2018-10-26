package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;

@RunWith(AndroidJUnit4.class)
public class Test {
    private UiDevice device;
    public Context context;
    public Instrumentation instrumentation;
    public String pkgsys = "com.ss.android.article.news";

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
    }

    @org.junit.Test
    public void test() throws UiObjectNotFoundException, FileNotFoundException {
        //启动APP
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        //登录
        LoginByJson login = new LoginByJson(device);
        login.login();
    }
}
