package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * 功能:
 *
 * @author: Hoo
 * Time: 11/21 12:04
 * Email：907486688@qq.com
 */

public class wandoujia {
    private UiDevice device;
    public Context context;
    public Instrumentation instrumentation;
    public String pkgsys = "com.wandoujia.phoenix2";

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
    }

    @Test
    public void testWandoujia() {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        testUtil.allowAuthority();
    }
}
