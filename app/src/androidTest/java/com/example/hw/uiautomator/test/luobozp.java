package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
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
import android.os.Bundle;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

/**
 * Created by Hoo on 2016/9/20 15:56
 * 邮箱：907486688@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class luobozp {
    private String TAG = "com.baidu.mtc.preui.test";
    private UiDevice device;
    private int width;
    private int height;
    public Instrumentation instrumentation;

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


        //获取运行时的参数
        Bundle args = InstrumentationRegistry.getArguments();
        //输出到运行报告中
        instrumentation.sendStatus(100, args);

        Bundle result = new Bundle();
        result.putString("key", "value");
        instrumentation.sendStatus(100, result);

        Bundle bundle = new Bundle();
        bundle.putString("Test","Hoo");
        instrumentation.sendStatus(666, bundle);

        String board = Build.BOARD.toString();
        Bundle bundle1 = new Bundle();
        bundle.putString("board",board);
        instrumentation.sendStatus(667,bundle1);


        device.pressHome();
        // 等待首屏加载
        final String launcherPackage = getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 2000);

        Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.chinaseit.bluecollar");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);
        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg("com.chinaseit.bluecollar").depth(0)), 5000);

        //进行进入APP操作,向左划屏4次后点击任一坐标进入应用
        SystemClock.sleep(5000);
        for (int i = 0; i < 4; i++) {
            device.swipe(width * 7 / 8, height / 2, width / 8, height / 2, 20);
            SystemClock.sleep(1000);
            if (i == 3) {
                device.click(width / 2, height / 2); //点击屏幕中点
                SystemClock.sleep(5000);
            }
        }
        device.click(width / 2, height / 2); //点击屏幕中点


        //截图保存
       // String f1 = "/data/1.bmp";
       // File f2 = new File(f1);
       // device.takeScreenshot(f2);
        //device.takeScreenshot(new File(""));


        SystemClock.sleep(2000);
        device.click(width / 2, height / 2); //点击屏幕中点
        SystemClock.sleep(2000);

        //进行登录操作
        SystemClock.sleep(3000);
        device.wait(Until.hasObject(By.text("我的")), 3000);

        UiObject my = device.findObject(new UiSelector().textContains("我的"));
        my.click();
        SystemClock.sleep(2000);
        UiObject username = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/userlogin_user_name"));
        UiObject password = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/userlogin_user_pwd"));
        UiObject loginbtn = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/userlogin_login"));
        if (username.exists()){
            username.clearTextField();
            username.setText("18862345472");
            password.clearTextField();
            password.setText("luobozhaopin");
            loginbtn.click();
            SystemClock.sleep(3000);
        }else {
            device.findObject(new UiSelector().textContains("我的")).click();
            username.clearTextField();
            username.setText("18862345472");
            password.clearTextField();
            password.setText("luobozhaopin");
            loginbtn.click();
            SystemClock.sleep(3000);
        }



        UiObject sy = device.findObject(new UiSelector().textContains("首页"));
        //点击首屏地址按钮
        if (sy.exists()) {
            sy.click();
            SystemClock.sleep(2000);
            UiObject city = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/tv_city"));
            city.click();
            SystemClock.sleep(3000);
            device.pressBack(); //返回主屏
        }

        //最新职位
        UiObject zw = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/choose_all"));
        if (zw.exists()) {
            zw.click();
            SystemClock.sleep(3000);
            device.pressBack();
        }

        //制造业
        UiObject make = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/choose_make"));
        if (make.exists()) {
            make.click();
            SystemClock.sleep(3000);
            device.pressBack();
        }

        //服务业
        UiObject service = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/choose_service"));
        if (service.exists()) {
            service.click();
            SystemClock.sleep(3000);
            device.pressBack();
        }

        //岗位一栏
        UiObject gw = device.findObject(new UiSelector().textContains("岗位"));
        if (gw.exists()) {
            gw.click();
        }

        UiObject message = device.findObject(new UiSelector().textContains("消息"));
        if (message.exists()) {
            message.click();
        }


        if (my.exists()) {
            my.click();
            device.wait(Until.hasObject(By.textContains("简历信息")), 3000);
            UiObject jl = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/tv_resume"));
            if (jl.exists()) {
                jl.click();
                SystemClock.sleep(2000);
                device.findObject(By.res("com.chinaseit.bluecollar:id/base_title_iv_back")).click();
                SystemClock.sleep(2000);
                UiObject set = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/fragment_my_setting"));
                if (set.exists()) {
                    set.click();
                    device.wait(Until.hasObject(By.textContains("注销")), 3000);
                    UiObject logout = device.findObject(new UiSelector().resourceId("com.chinaseit.bluecollar:id/tv_setting_loginout"));
                    if (logout.exists()) {
                        logout.click();
                        SystemClock.sleep(2000);
                        device.findObject(By.text("确定").res("android:id/button1")).click();
                    }
                }
            }
        }


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
