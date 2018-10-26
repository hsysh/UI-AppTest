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
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.example.hw.uiautomator.util.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Random;

/**
 * Created by Hoo on 10/13 14:06
 * 邮箱：907486688@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class hezi_monkey {

    private UiDevice device;
    public Context context;
    public Instrumentation instrumentation;
    private int width;
    private int height;
    public int minute = 10;
    public String pkgsys = "com.iboxpay.wallet";

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
        width = device.getDisplayWidth();
        height = device.getDisplayHeight();
    }

    @Test
    public void test() throws UiObjectNotFoundException {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        testUtil.allowAuthority();
        login();
        if (testUtil.performClickByText("付款码")) {
            testUtil.printLog("完成点击付款码操作");
            testUtil.pressBack();
        }
        if (testUtil.performClickByText("收款")) {
            testUtil.printLog("完成点击收款操作");
            testUtil.pressBack();
        }
        if (testUtil.performClickByText("个税计算")) {
            testUtil.printLog("完成点击个税计算操作");
            UiObject mon = device.findObject(new UiSelector().className("android.widget.EditText").index(6));
            if (mon.exists()) {
                mon.setText("1000");
                testUtil.pressBack();
            }
        } else {
            testUtil.pressBack();
        }
        if (device.hasObject(By.text("个税计算")) == true && device.hasObject(By.text("房贷计算")) == false) {
            testUtil.pressBack();
        } else {
            testUtil.printLog("退出个税计算失败");
        }
        if (testUtil.performClickByText("房贷计算")) {
            testUtil.printLog("完成点击房贷计算操作");
            testUtil.pressBack();
        }
        if (testUtil.performClickByText("保险超市")) {
            testUtil.printLog("完成点击保险超市操作");
            testUtil.pressBack();
        }
        if (testUtil.performClickByText("理财")) {
            testUtil.printLog("完成点击理财操作");
            if (testUtil.performClickByResID("com.iboxpay.wallet:id/iv_hui")) {
                testUtil.printLog("完成点击理财会操作");
                if (testUtil.performClickByText("关闭")) {
                    testUtil.printLog("完成点击关闭操作");
                } else {
                    testUtil.pressBack();
                }
            }
        } else {
            testUtil.printLog("打开理财失败");
        }
        if (testUtil.performClickByText("我的")) {
            testUtil.printLog("完成点击我的操作");
            SystemClock.sleep(2000);
            if (testUtil.performClickByText("设置")) {
                testUtil.printLog("完成点击设置操作");
                SystemClock.sleep(2000);
                if (testUtil.performClickByText("银行卡")) {
                    testUtil.printLog("完成点击银行卡操作");
                    if (testUtil.performClickByContainsText("中国工商")) {
                        testUtil.printLog("完成点击中国工商银行银行卡操作");
                        SystemClock.sleep(2000);
                        if (testUtil.performClickByResID("com.iboxpay.wallet:id/manager_bank")) {
                            testUtil.printLog("完成点击管理操作");
                            SystemClock.sleep(1000);
                            if (testUtil.performClickByResID("com.iboxpay.wallet:id/cancelBtn")) {
                                testUtil.printLog("完成点击取消操作");
                                SystemClock.sleep(1000);
                            } else {
                                testUtil.pressBack();
                            }
                            testUtil.pressBack();
                        } else {
                            testUtil.pressBack();
                        }
                        testUtil.pressBack();
                    } else {
                        testUtil.pressBack();
                    }
                    testUtil.pressBack();
                } else {
                    testUtil.pressBack();
                }
            }
            if (device.hasObject(By.textContains("登录密码")) == true && device.hasObject(By.text("买啦")) == false) {
                testUtil.pressBack();
            } else if (device.hasObject(By.textContains("添加")) == true && device.hasObject(By.text("买啦")) == false) {
                testUtil.pressBack();
                testUtil.pressBack();
            } else if (device.hasObject(By.text("买啦"))) {
                testUtil.printLog("成功退出银行卡界面");
            } else {
                testUtil.printLog("退出银行卡界面失败");
            }
            if (testUtil.performClickByText("买啦")) {
                testUtil.printLog("完成点击买啦操作");
                testUtil.pressBack();
            }
            if (testUtil.performClickByText("我的客服")) {
                testUtil.printLog("完成点击我的客服操作");
                testUtil.pressBack();
            }
            if (testUtil.performClickByText("汇赚钱")) {
                testUtil.printLog("完成点击汇赚钱操作");
                testUtil.pressBack();
            }
            if (testUtil.performClickByText("帮你还")) {
                testUtil.printLog("完成点击帮你还操作");
                testUtil.pressBack();
            }
            if (testUtil.performClickByResID("com.iboxpay.wallet:id/iv_money_toggle")) {
                testUtil.printLog("完成隐藏金额操作");
            }
            width = device.getDisplayWidth();
            height = device.getDisplayHeight();
            device.swipe(width / 2, height / 4, width / 2, width * 3 / 2, 100);
            if (testUtil.performClickByResID("com.iboxpay.wallet:id/tv_phone_number")) {
                testUtil.printLog("完成点击昵称操作");
                if (testUtil.performClickByText("账户详情")) {
                    testUtil.printLog("完成点击账户详情操作");
                    if (testUtil.performClickByText("性别")) {
                        testUtil.printLog("完成点击性别操作");
                        if (testUtil.performClickByText("男")) {
                            testUtil.printLog("完成点击\"男\"操作");
                        }
                        testUtil.pressBack();
                    }
                }
                if (testUtil.performClickByText("退出登录")) {
                    testUtil.printLog("完成退出操作");
                }
                testUtil.pressBack();
                if (device.hasObject(By.text("注册"))) {
                    testUtil.pressBack();
                }
                if (testUtil.performClickByText("首页")) {
                    testUtil.printLog("完成点击首页操作");
                }
//                else {
//                    System.exit(0);
//                }
            }
        } else {
            testUtil.printLog("打开我的失败");
        }

        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > minute * 60 * 1000) {
                break;
            } else if ((device.hasObject(By.text("注册")) == true) && (device.hasObject(By.textContains("手机号将作为唯一账号")) == false)) {
                login();
            } else if (device.hasObject(By.textContains("手机号将作为唯一账号"))) {
                testUtil.pressBack();
            } else if ((device.getCurrentPackageName()).equals(pkgsys)) {
                clickEle();
            } else {
                testUtil.startTargetApp();
                clickEle();
            }
        }

    }


    public void clickEle() throws UiObjectNotFoundException {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        List<UiObject2> list = device.findObjects(By.enabled(true).clickable(true).pkg(pkgsys));
        int i = (int) (Math.random() * 20);
        if (i < list.size()) {
            if (list.get(i) != null) {
                list.get(i).click();
                SystemClock.sleep(1000);
                if ((device.hasObject(By.text("注册")) == true) && (device.hasObject(By.textContains("手机号将作为唯一账号")) == false)) {
                    login();
                } else if (device.hasObject(By.text("首页")) == false && (device.hasObject(By.text("设置")) == false) && (device.hasObject(By.text("账户详情")) == false)) {
                    testUtil.pressBack();
                }
            } else {
                clickEle();
            }
        } else {
            clickEle();
        }

    }

    public void login() throws UiObjectNotFoundException {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        if (testUtil.performClickByText("登录")) {
            testUtil.printLog("成功打开登录页面");
            SystemClock.sleep(2000);
            //输入用户名
            UiObject user = device.findObject(new UiSelector().resourceId("com.iboxpay.wallet:id/et_edittext").instance(0));
            user.click();
            user.clearTextField();
            user.setText("18688394078");
            UiObject pwd = device.findObject(new UiSelector().className("android.widget.EditText").instance(1));
            SystemClock.sleep(1000);
            pwd.click();
            pwd.setText("qwe123");
            if (testUtil.performClickByResID("com.iboxpay.wallet:id/sign_in_button")) {
                testUtil.printLog("完成登录操作");
            } else {
                testUtil.printLog("登录操作失败");
            }

        }

    }

    public void monkey() {
        TestUtil testUtil1 = new TestUtil(device, context, pkgsys);
        if ((device.getCurrentPackageName()).equals(pkgsys)) {
            int clickwidth = (int) (Math.random() * 1000);
            int clickheigt = (int) (Math.random() * 1000);
            Random rd = new Random();
            //   device.click(rd.nextInt(100-width)*1000,rd.nextInt(100-height)*1000);
            if (clickheigt < height && clickwidth < width) {
                device.click(clickwidth, clickheigt);
            } else {
                monkey();
            }
            device.findObject(By.checkable(true).checked(false));
            SystemClock.sleep(1000);
            if ((device.hasObject(By.text("首页")) == false) || (device.hasObject(By.text("理财")) == false) || (device.hasObject(By.text("我的")) == false)) {
                testUtil1.pressBack();
            }
        } else {
            testUtil1.printLog("超出APP范围");
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
