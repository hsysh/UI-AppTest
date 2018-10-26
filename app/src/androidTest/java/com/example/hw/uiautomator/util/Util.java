package com.example.hw.uiautomator.util;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 功能:
 *
 * @author: Hoo
 * Time: 11/2 09:36
 * Email：907486688@qq.com
 */

public class Util {
    public static String fetchUserName() {
        //String baseUrl = "http://112.80.58.210:60000/interface/hello/world";
        String baseUrl = "http://112.80.58.210:60000/testspace/api/client/v1/account/getAccount";
        try {
            URL url = new URL(baseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream is = conn.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            JSONObject jsonObject = new JSONObject(b.toString());
            conn.disconnect();
            return jsonObject.getString("account");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
