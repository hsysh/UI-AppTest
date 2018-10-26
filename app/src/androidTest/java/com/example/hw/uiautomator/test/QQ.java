package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * 功能:
 *
 * @author: Hoo
 * Time: 11/17 14:17
 * Email：907486688@qq.com
 */


public class QQ {
    private UiDevice device;
    public Context context;
    public Instrumentation instrumentation;
    public String pkgsys = "com.tencent.mobileqq";
    public int minute = 9;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
    }

    @Test
    public void testQQ() {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        testUtil.allowAuthority();
        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > minute * 60 * 1000) {
                break;
            } else {
                testUtil.inputTextByDesc("请输入QQ号码或手机或邮箱", "111111");
                testUtil.inputTextByResId("com.tencent.mobileqq:id/password", "1111111");
                testUtil.performClickByResID("com.tencent.mobileqq:id/login");
                SystemClock.sleep(2000);
                testUtil.performClickByResID("com.tencent.mobileqq:id/ivTitleBtnLeftButton");
                SystemClock.sleep(2000);
                testUtil.clearTextByDesc("请输入QQ号码或手机或邮箱");
                testUtil.clearTextByResId("com.tencent.mobileqq:id/password");
                SystemClock.sleep(2000);
            }
        }
    }
}
