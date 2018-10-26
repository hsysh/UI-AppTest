package com.example.hw.uiautomator.test;

import android.os.Environment;
import android.os.SystemClock;
import android.support.test.uiautomator.UiDevice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;

import java.util.Map.Entry;


public class LoginUtil {
    public UiDevice mUiDevice;
    public int mwidth;
    public int mheight;

    public LoginUtil(UiDevice uiDevice) {
        this.mUiDevice = uiDevice;
        this.mwidth = uiDevice.getDisplayWidth();
        this.mheight = uiDevice.getDisplayHeight();
    }

    public void login() throws FileNotFoundException, JsonIOException, JsonSyntaxException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject) parser.parse(new FileReader(Environment.getExternalStorageDirectory()
                + File.separator + "data.json"));
        Set<Entry<String, JsonElement>> entrySet = jsonObj.entrySet();
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            JsonArray jsonArray = jsonObj.get(key).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject subObject = jsonArray.get(i).getAsJsonObject();
                String action = subObject.get("action").getAsString();
                String By = subObject.get("By").getAsString();
                switch (action) {
                    case "swipe":
                        int times = subObject.get("times").getAsInt();
                        swipe(mUiDevice, By, times);
                        break;
                    case "setText":
                        String idOrtext1 = subObject.get("idOrtext").getAsString();
                        String text = subObject.get("text").getAsString();
                        perform(mUiDevice, By, idOrtext1, text);
                        break;
                    case "click":
                        String idOrtext2 = subObject.get("idOrtext").getAsString();
                        perform(mUiDevice, By, idOrtext2);
                        break;
                }
            }
        }
    }

    //点击
    public void perform(UiDevice device, String By, String idOrtext) {
        switch (By) {
            case "id":
                device.findObject(android.support.test.uiautomator.By.res(idOrtext)).click();
                SystemClock.sleep(1000);
                break;
            case "text":
                device.findObject(android.support.test.uiautomator.By.text(idOrtext)).click();
                SystemClock.sleep(1000);
                break;
            case "textContains":
                device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).click();
                SystemClock.sleep(1000);
                break;
        }

    }

    //输入文本
    public void perform(UiDevice device, String By, String idOrtext, String text) {
        switch (By) {
            case "id":
                device.findObject(android.support.test.uiautomator.By.res(idOrtext)).clear();
                device.findObject(android.support.test.uiautomator.By.res(idOrtext)).setText(text);
                SystemClock.sleep(1000);
                break;
            case "text":
                device.findObject(android.support.test.uiautomator.By.res(idOrtext)).clear();
                device.findObject(android.support.test.uiautomator.By.text(idOrtext)).setText(text);
                SystemClock.sleep(1000);
                break;
            case "textContains":
                device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).clear();
                device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).setText(text);
                SystemClock.sleep(1000);
                break;
        }
    }

    //滑动
    public void swipe(UiDevice device, String dir, int times) {
        if (times > 0) {
            switch (dir) {
                case "left":
                    for (int i = 0; i < times; i++) {
                        device.swipe(mwidth * 5 / 6, mheight / 2, mwidth / 6, mheight / 2, 20);
                    }
                    break;
                case "right":
                    for (int i = 0; i < times; i++) {
                        device.swipe(mwidth / 6, mheight / 2, mwidth * 5 / 6, mheight / 2, 20);
                    }
                    break;
                case "up":
                    for (int i = 0; i < times; i++) {
                        device.swipe(mwidth / 2, mheight * 5 / 6, mwidth / 2, mheight / 6, 20);
                    }
                    break;
                case "down":
                    for (int i = 0; i < times; i++) {
                        device.swipe(mwidth / 2, mheight / 6, mwidth / 2, mheight * 5 / 6, 20);
                    }
                    break;
            }
        }
    }
}
