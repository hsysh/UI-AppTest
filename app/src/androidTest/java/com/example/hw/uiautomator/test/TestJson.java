package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;


import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class TestJson {
    private UiDevice device;
    public Instrumentation instrumentation;
    public int width;
    public int height;
    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
    }

    @Test
    public void testLogin() throws UiObjectNotFoundException {
        width = device.getDisplayWidth();
        height = device.getDisplayHeight();
        device.pressHome();
        final String launcherPackage = getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 2000);
        Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.zjwh.android_wh_physicalfitness");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg("com.zjwh.android_wh_physicalfitness").depth(0)), 5000);
        UiObject my = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_login"));
        my.click();

        try {
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(new FileReader( Environment.getExternalStorageDirectory()
                    // + File.separator +"TestData"
                    + File.separator  +"data.json"));
            //用户名
            JsonArray userarray = object.get("username").getAsJsonArray();
            for (int i = 0; i < userarray.size(); i++) {
                JsonObject subObject = userarray.get(i).getAsJsonObject();
                String action = subObject.get("action").getAsString();
                String By = subObject.get("By").getAsString();
                String idOrtext = subObject.get("idOrtext").getAsString();
                String text = subObject.get("text").getAsString();
                perform(device, action, By, idOrtext, text);
                SystemClock.sleep(2000);
            }
            //密码
            JsonArray pwdarray = object.get("password").getAsJsonArray();
            for (int i = 0; i < pwdarray.size(); i++) {
                JsonObject subObject = pwdarray.get(i).getAsJsonObject();
                String action = subObject.get("action").getAsString();
                String By = subObject.get("By").getAsString();
                String idOrtext = subObject.get("idOrtext").getAsString();
                String text = subObject.get("text").getAsString();
                perform(device, action, By, idOrtext, text);
                SystemClock.sleep(2000);
            }

            //确认按钮
            JsonArray submitarray = object.get("submit").getAsJsonArray();
            for (int i = 0; i < submitarray.size(); i++) {
                JsonObject subObject = submitarray.get(i).getAsJsonObject();
                String action = subObject.get("action").getAsString();
                String By = subObject.get("By").getAsString();
                String idOrtext = subObject.get("idOrtext").getAsString();
                perform(device, action, By, idOrtext, "");
                SystemClock.sleep(2000);
            }

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void perform(UiDevice udevice, String action, String By, String idOrtext, String text) {
        switch (action) {
            case "click":
                switch (By) {
                    case "id":
                        udevice.findObject(android.support.test.uiautomator.By.res(idOrtext)).click();
                        break;
                    case "text":
                        udevice.findObject(android.support.test.uiautomator.By.text(idOrtext)).click();
                        break;
                }
                break;
            case "setText":
                switch (By) {
                    case "id":
                        udevice.findObject(android.support.test.uiautomator.By.res(idOrtext)).setText(text);
                        break;
                    case "text":
                        udevice.findObject(android.support.test.uiautomator.By.text(idOrtext)).setText(text);
                        break;
                }
        }
    }

    public void swipe(UiDevice udevice, String dir, int times) {
        switch (dir) {
            case "left":
                for (int i = 0; i < times; i++) {
                    udevice.swipe(width * 5 / 6, height / 2, width / 6, height / 2, 20);
                }
                break;
            case "right":
                for (int i = 0; i < times; i++) {
                    udevice.swipe(width / 6, height / 2, width * 5 / 6, height / 2, 20);
                }
                break;
            case "up":
                for (int i = 0; i < times; i++) {
                    udevice.swipe(width / 2, height * 5 / 6, width / 2, height / 6, 20);
                }
                break;
            case "down":
                for (int i = 0; i < times; i++) {
                    udevice.swipe(width / 2, height / 6, width / 2, height * 5 / 6, 20);
                }
                break;
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
