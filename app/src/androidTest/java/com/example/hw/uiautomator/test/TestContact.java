package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 功能:
 *
 * @author: Hoo
 * Time: 11/25 14:54
 * Email：907486688@qq.com
 */

@RunWith(AndroidJUnit4.class)
public class TestContact {

    private UiDevice uiDevice;
    private Instrumentation instrumentation;
    private Context mContext;
    private TestUtil testUtil;
    private String tarPkgString;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        uiDevice = UiDevice.getInstance(instrumentation);
        mContext = instrumentation.getTargetContext();
        tarPkgString = "com.example.android.contactmanager";
        testUtil=new TestUtil(uiDevice, mContext,tarPkgString);
        testUtil.startTest();
    }
    @Test
    public void testContact() throws UiObjectNotFoundException {
        testUtil.allowAuthority();
        testUtil.printLog("进入APP");
        SystemClock.sleep(3000);

        if (testUtil.performClickByResID("com.example.android.contactmanager:id/addContactButton")){
            testUtil.printLog("点击添加按钮");
        }else {
            testUtil.printLog("没有点击添加那妞");
        }

        if (testUtil.hasObjectByID("com.example.android.contactmanager:id/contactNameEditText")) {
            if (testUtil.inputTextByResId("com.example.android.contactmanager:id/contactNameEditText","1111")){
                testUtil.printLog("输入姓名");
            }
            if (testUtil.inputTextByResId("com.example.android.contactmanager:id/contactPhoneEditText","22222")){
                testUtil.printLog("输入手机");
            }
            if (testUtil.inputTextByResId("com.example.android.contactmanager:id/contactEmailEditText","33333")){
                testUtil.printLog("输入邮箱");
            }

        }
        if (testUtil.hasObjectByID("com.example.android.contactmanager:id/contactSaveButton")){
            if (testUtil.performClickByResID("com.example.android.contactmanager:id/contactSaveButton")){
                testUtil.printLog("点击保存按钮");
            }else {
                testUtil.printLog("没有点击保存按钮");
            }
        }
        testUtil.allowAuthority();
    }
}
