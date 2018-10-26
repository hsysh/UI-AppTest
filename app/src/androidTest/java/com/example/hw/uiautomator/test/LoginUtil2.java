package com.example.hw.uiautomator.test;

import android.os.Environment;
import android.os.SystemClock;
import android.support.test.uiautomator.UiDevice;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;
import java.util.Map.Entry;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 读取没有节点,纯数组的json文件内容
 * gson方式
 */

public class LoginUtil2 {
    public UiDevice mUiDevice;
    public int mwidth;
    public int mheight;

    public LoginUtil2(UiDevice uiDevice) {
        this.mUiDevice = uiDevice;
        this.mwidth = uiDevice.getDisplayWidth();
        this.mheight = uiDevice.getDisplayHeight();
    }

    public void login() throws FileNotFoundException, JsonIOException, JsonSyntaxException {
        String fileName = new String(Environment.getExternalStorageDirectory()
                + File.separator + "DataTest.json");
        String jsonStr;
        JsonParser parser = new JsonParser();
        jsonStr = readFileByLines(fileName);
        JsonArray jarr = parser.parse(jsonStr).getAsJsonArray();
        List<JsonObject> strList = toList(jarr);

        for (JsonObject jobj : strList) {
            // 获取集合中的对象
            TreeMap<String, Object> map = toMap(jobj);
            // 第一个一定是action
            String action = map.get("action") != null ? map.get("action").toString().replace("\"",""): "";
            String by = map.get("By") != null ? map.get("By").toString().replace("\"",""): "";
            String idOrtext = map.get("idOrtext") != null ? map.get("idOrtext").toString(): "";
            String text = map.get("text") != null ? map.get("text").toString(): "";
            String times = map.get("times") != null ? map.get("times").toString() : "";
            if (idOrtext.length()>2){
                idOrtext=idOrtext.substring(1,idOrtext.length()-1);
            }
            if (text.length()>2){
                text=text.substring(1,text.length()-1);
            }
            switch (action) {
                case "swipe":
                    swipe(mUiDevice,by,Integer.parseInt(times));
                    break;
                case "click":
                    perform(mUiDevice,by,idOrtext);
                    break;
                case "setText":
                    perform(mUiDevice,by,idOrtext,text);
                    break;
            }
        }
    }

    // 转换为集合对象
    public static List<JsonObject> toList(JsonArray json) {
        List<JsonObject> list = new ArrayList<JsonObject>();
        for (int i = 0; i < json.size(); i++) {
            JsonObject value = json.get(i).getAsJsonObject();
            list.add(value);
        }
        return list;
    }

    // 获取参数集合
    public static List<String> getParList(JsonObject json) {
        List<String> parList = new ArrayList<String>();
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            parList.add(key);
        }
        return parList;
    }

    // 转换成map
    public static TreeMap<String, Object> toMap(JsonObject json) {
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else
                map.put((String) key, value);
        }
        return map;
    }

    public static String readFileByLines(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
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
