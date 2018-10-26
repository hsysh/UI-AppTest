package com.example.hw.uiautomator.test;

import android.os.Environment;
import android.os.SystemClock;
import android.support.test.uiautomator.UiDevice;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 使用org.json读取json文件内容
 */

public class LoginByJson {
    public UiDevice mUiDevice;
    public int mwidth;
    public int mheight;

    public LoginByJson(UiDevice uiDevice) {
        this.mUiDevice = uiDevice;
        this.mwidth = uiDevice.getDisplayWidth();
        this.mheight = uiDevice.getDisplayHeight();
    }

    public void login() throws FileNotFoundException {
        //String fileName = "D:\\文档\\其他文件\\data.json";
        String fileName = new String(Environment.getExternalStorageDirectory() + File.separator + "DataTest.json");
        String jsonStr;
        jsonStr = readFileByLines(fileName);
        try {
            JSONArray jarr = new JSONArray(jsonStr);
            //依照每行的内容进行读取操作
            for (int i = 0; i < jarr.length(); i++) {
                String action = jarr.getJSONObject(i).getString("action") != null ? jarr.getJSONObject(i).getString("action").toString() : "";
                String By = jarr.getJSONObject(i).getString("By") != null ? jarr.getJSONObject(i).getString("By").toString() : "";
                switch (action) {
                    case "swipe":
                        String times = jarr.getJSONObject(i).getString("times").toString();
                        swipe(mUiDevice, By, Integer.parseInt(times));
                        break;
                    case "click":
                        String idOrtext = jarr.getJSONObject(i).getString("idOrtext").toString() != null ? jarr.getJSONObject(i).getString("idOrtext").toString() : "";
                        perform(mUiDevice, By, idOrtext);
                        break;
                    case "setText":
                        String idOrtext1 = jarr.getJSONObject(i).getString("idOrtext").toString() != null ? jarr.getJSONObject(i).getString("idOrtext").toString() : "";
                        String text = jarr.getJSONObject(i).getString("text") != null ? jarr.getJSONObject(i).getString("text").toString() : "";
                        perform(mUiDevice, By, idOrtext1, text);
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                if (device.findObject(android.support.test.uiautomator.By.res(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.res(idOrtext)).click();
                    SystemClock.sleep(1000);
                }
                break;
            case "text":
                if (device.findObject(android.support.test.uiautomator.By.text(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.text(idOrtext)).click();
                    SystemClock.sleep(1000);
                }
                break;
            case "textContains":
                if (device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).click();
                    SystemClock.sleep(1000);
                }
                break;
        }

    }

    //输入文本
    public void perform(UiDevice device, String By, String idOrtext, String text) {
        switch (By) {
            case "id":
                if (device.findObject(android.support.test.uiautomator.By.res(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.res(idOrtext)).clear();
                    device.findObject(android.support.test.uiautomator.By.res(idOrtext)).setText(text);
                    SystemClock.sleep(1000);
                }
                break;
            case "text":
                if (device.findObject(android.support.test.uiautomator.By.res(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.res(idOrtext)).clear();
                    device.findObject(android.support.test.uiautomator.By.text(idOrtext)).setText(text);
                    SystemClock.sleep(1000);
                }
                break;
            case "textContains":
                if (device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)) != null) {
                    device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).clear();
                    device.findObject(android.support.test.uiautomator.By.textContains(idOrtext)).setText(text);
                    SystemClock.sleep(1000);
                }
                break;
        }
    }

    //滑动
    public void swipe(UiDevice device, String By, int times) {
        if (times > 0) {
            switch (By) {
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
