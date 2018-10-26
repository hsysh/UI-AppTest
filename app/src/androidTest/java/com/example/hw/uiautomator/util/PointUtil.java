package com.example.hw.uiautomator.util;

import android.graphics.Point;

/**
 * Created by weize on 2016/3/22.
 */
public class PointUtil {
    public static double distanceOf(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
