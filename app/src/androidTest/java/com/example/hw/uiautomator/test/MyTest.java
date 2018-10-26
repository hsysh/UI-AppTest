package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;


import java.io.File;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Date;

/**
 * 使用uiautomator框架，按照一定的规则执行一连串事件，模拟用户登录
 */
@RunWith(AndroidJUnit4.class)
public class MyTest {
    private String TAG = "com.baidu.mtc.preui.test";
    private UiDevice device;
    private int width;
    private int height;
    private Instrumentation instrumentation;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
    }

    @Test
    public void testLogin() throws UiObjectNotFoundException {
        width = device.getDisplayWidth();
        height = device.getDisplayHeight();
        Log.i(TAG, "width:" + width + "height:" + height);

        device.pressHome();
        // 等待首屏加载
        final String launcherPackage = getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 2000);

        Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.baidu.mtc.preui");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);
        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg("com.baidu.mtc.preui").depth(0)), 5000);

//        Bundle argBundle = InstrumentationRegistry.getArguments();
//        String emailStr = argBundle.getString("email");
//        String passwordStr = argBundle.getString("password");

        SystemClock.sleep(2000);
        UiObject email = device.findObject(new UiSelector().className("android.widget.EditText").instance(0));
        email.click();
        SystemClock.sleep(1000);

        // 这里需要注意一下，如果SDK_VERSION < 19 ,并且有中文等特殊字符，需要用Utf7ImeHelper.e进行编码
        email.setText("heLlo123");
        // email.setText(Utf7ImeHelper.e("heLlo123@韦泽"));
//        email.setText(emailStr);
        SystemClock.sleep(1500);

        UiObject password = device.findObject(new UiSelector().className("android.widget.EditText").instance(1));
        password.click();
        SystemClock.sleep(1000);

        // 这里需要注意一下，如果SDK_VERSION < 19 ,并且有中文等特殊字符，需要用Utf7ImeHelper.e进行编码
        password.setText("231wOrld");
//        password.setText(passwordStr);
        SystemClock.sleep(1500);

        device.findObject(new UiSelector().className("android.widget.Button").instance(0)).click();
        SystemClock.sleep(5000);

    }


    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = instrumentation.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }


}