package com.example.hw.uiautomator.util;

import android.app.Instrumentation;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;


/**
 * 功能:
 *
 * @author: Hoo
 * Time: 11/17 14:15
 * Email：907486688@qq.com
 */


public class TestUtil {
    private UiDevice mUiDevice;
    private Context mContext;
    private int mTimeOut = 10000;
    private String mTarPkgString;
    public PowerManager.WakeLock wakeLock;
    public Instrumentation minstrumentation;

    public TestUtil(UiDevice uiDevice, Context context, String pkgString) {
        this.mUiDevice = uiDevice;
        this.mContext = context;
        this.mTarPkgString = pkgString;
    }

    public boolean startTargetApp() {
        PackageManager pm = mContext.getPackageManager();

        Intent targetIntent = pm.getLaunchIntentForPackage(mTarPkgString);
        if (targetIntent == null)
            return false;
        targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(targetIntent);
        long ts = System.currentTimeMillis();
        if (mUiDevice == null)
            this.mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        while (!hasObjectByTextContains("权限")
                && !hasObjectByTextContains("安全警告")
                && !hasObjectByTextContains("是否允许")
                && !hasObjectByTextContains("要允许")
                && !hasObjectByTextContains("授权")
                && !hasObjectByTextContains("安全")
                && !hasObjectByTextContains("访问")
                && !hasObjectByTextContains("注意")
                && !mUiDevice.hasObject(By.enabled(true).pkg(mTarPkgString))
                ) {
            if ((System.currentTimeMillis() - ts) > 1000 * 30) {
                return false;
            }
        }

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allowAuthority();

        ts = System.currentTimeMillis();

        try {
            while (!mUiDevice.hasObject(By.clickable(true).pkg(mTarPkgString))
                    && !mUiDevice.hasObject(By.scrollable(true).pkg(mTarPkgString))) {
                if ((System.currentTimeMillis() - ts) > 1000 * 10)
                    break;
            }
        } catch (NullPointerException e) {
        }
        return true;
    }

    public boolean performClickByResID(String id) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.res(id).pkg(mTarPkgString)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        UiObject2 object2 = mUiDevice.findObject(By.res(id).pkg(mTarPkgString));
        if (object2 != null) {
            object2.click();
            SystemClock.sleep(1500);
            mUiDevice.waitForIdle();
            return true;
        } else {
            return false;
        }
    }

    public boolean performClickByResIdandInstance(String id, int instance) throws UiObjectNotFoundException {
        mUiDevice.wait(Until.findObject(By.res(id).pkg(mTarPkgString)), mTimeOut);
        UiObject object = mUiDevice.findObject(new UiSelector().resourceId(id).instance(instance));

        if (object != null) {
            object.click();
            // long ts = System.currentTimeMillis();
            // String capturePNG = String.valueOf(ts) + ".png";
            //  Util.recordPerformance(ts, capturePNG, object2.getVisibleCenter(), mContext, mUiDevice, mTarPkgString);
            SystemClock.sleep(2000);
            // Util.captureScreen(String.valueOf(ts), mUiDevice);
            return true;
        } else {
            return false;
        }
    }

    public boolean performClickByText(String text) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.text(text)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        UiObject2 object2 = mUiDevice.findObject(By.text(text));
        if (object2 != null) {
            object2.click();
            SystemClock.sleep(1500);
            mUiDevice.waitForIdle();
            return true;
        } else {
            return false;
        }
    }

    public boolean performClickByContainsText(String text) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.textContains(text)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        UiObject2 object2 = mUiDevice.findObject(By.textContains(text));

        if (object2 != null) {
            object2.click();
            SystemClock.sleep(1500);
            mUiDevice.waitForIdle();
            return true;
        } else {
            return false;
        }
    }

    public boolean inputTextByResId(String res, String text) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.res(res).pkg(mTarPkgString)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        UiObject2 object2 = mUiDevice.findObject(By.res(res));
        try {
            if (object2 != null) {
                object2.setText(text);
                SystemClock.sleep(1500);
                return true;
            }
        } catch (NullPointerException e) {
            SystemClock.sleep(1500);
            object2 = mUiDevice.findObject(By.res(res));
            if (object2 != null) {
                object2.setText(text);
                SystemClock.sleep(1500);
                return true;
            }
        }
        return false;
    }

    public boolean inputTextByDesc(String desc, String text) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.desc(desc).pkg(mTarPkgString)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }

        UiObject2 object2 = mUiDevice.findObject(By.desc(desc));
        try {
            if (object2 != null) {
                object2.setText(text);
                SystemClock.sleep(1500);
                return true;
            }
        } catch (NullPointerException e) {
            SystemClock.sleep(1500);
            object2 = mUiDevice.findObject(By.desc(desc));
            if (object2 != null) {
                object2.setText(text);
                SystemClock.sleep(1500);
                return true;
            }
        }
        return false;
    }

    public boolean clearTextByDesc(String desc) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.desc(desc).pkg(mTarPkgString)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }

        UiObject2 object2 = mUiDevice.findObject(By.desc(desc));
        try {
            if (object2 != null) {
                object2.clear();
                SystemClock.sleep(1500);
                return true;
            }
        } catch (NullPointerException e) {
            SystemClock.sleep(1500);
            object2 = mUiDevice.findObject(By.desc(desc));
            if (object2 != null) {
                object2.clear();
                SystemClock.sleep(1500);
                return true;
            }
        }
        return false;
    }

    public boolean clearTextByResId(String res) {
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                mUiDevice.wait(Until.findObject(By.res(res).pkg(mTarPkgString)), mTimeOut);
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }

        UiObject2 object2 = mUiDevice.findObject(By.res(res));
        try {
            if (object2 != null) {
                object2.clear();
                SystemClock.sleep(1500);
                return true;
            }
        } catch (NullPointerException e) {
            SystemClock.sleep(1500);
            object2 = mUiDevice.findObject(By.res(res));
            if (object2 != null) {
                object2.clear();
                SystemClock.sleep(1500);
                return true;
            }
        }
        return false;
    }


    public boolean hasObjectByText(String text) {
        boolean has = false;
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5)
                return false;
            try {
                has = mUiDevice.hasObject(By.text(text));
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        return has;
    }

    public boolean hasObjectByTextContains(String text) {
        boolean has = false;
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5) return false;
            try {
                has = mUiDevice.hasObject(By.textContains(text));
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        return has;
    }

    public boolean hasObjectByID(String id) {
        boolean has = false;
        boolean isNull = true;
        int i = 0;
        while (isNull) {
            if (i > 5) return false;
            try {
                has = mUiDevice.hasObject(By.res(id));
                isNull = false;
            } catch (NullPointerException e) {
                SystemClock.sleep(2000);
                isNull = true;
            }
            i++;
        }
        return has;
    }

    public void allowAuthority() throws NullPointerException {
        if (mUiDevice.hasObject(By.textContains("是否允许"))
                || mUiDevice.hasObject(By.textContains("授权"))
                || mUiDevice.hasObject(By.textContains("权限"))
                || mUiDevice.hasObject(By.textContains("安全"))
                || mUiDevice.hasObject(By.textContains("位置"))
                || mUiDevice.hasObject(By.textContains("要允许"))
                || mUiDevice.hasObject(By.textContains("定位"))
                || mUiDevice.hasObject(By.textContains("相机"))
                || mUiDevice.hasObject(By.textContains("识别码"))
                || mUiDevice.hasObject(By.textContains("媒体内容"))
                || mUiDevice.hasObject(By.textContains("存储权限"))
                || mUiDevice.hasObject(By.textContains("文件"))) {

            String pkgSys = mUiDevice.getCurrentPackageName();
            UiObject2 chkObject = mUiDevice.findObject(By.checkable(true).checked(false).pkg(pkgSys));
            if (chkObject != null) {
                try {
                    chkObject.click();
                } catch (StaleObjectException e) {
                    e.printStackTrace();
                }
                SystemClock.sleep(500);
            }
            while (performClickByText("允许")) {
                chkObject = mUiDevice.findObject(By.checkable(true).checked(false).pkg(pkgSys));
                if (chkObject != null) {
                    try {
                        chkObject.click();
                    } catch (StaleObjectException e) {
                        e.printStackTrace();
                    }
                }
                SystemClock.sleep(2000);
            }
        }
    }

    public void pressBack() {
        mUiDevice.pressBack();
        SystemClock.sleep(500);
    }

    public void pressDelete() {
        mUiDevice.pressDelete();
        SystemClock.sleep(500);
    }

    public void pressEnter() {
        mUiDevice.pressEnter();
        SystemClock.sleep(500);
    }

    public void startTest() {
        wakeAndUnlock(mContext);
        startTargetApp();
    }

    public void endTest() {
        wakeLock.release();
    }

    public void printLog(String log) {

        Bundle bundle = new Bundle();
        bundle.putString(log, log);
        minstrumentation.sendStatus(100, bundle);

    }

    //唤醒屏幕解锁
    public void wakeAndUnlock(Context context) {
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //获取电源管理器对象
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");

        //点亮屏幕
        wakeLock.acquire();
        //解锁
        if (km.isKeyguardLocked()) {
            kl.disableKeyguard();
        }
    }
}
