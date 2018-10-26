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
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Hoo on 2016/9/7 11:51
 * 邮箱：907486688@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class PhysicalfitnessTest {

    private String TAG = "c.test";
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
    public void Testin() throws UiObjectNotFoundException {

        width = device.getDisplayWidth();
        height = device.getDisplayHeight();
        Log.i(TAG, "width:" + width + "height:" + height);

        device.pressHome();
        // 等待首屏加载
        final String launcherPackage = getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 2000);

        // Launch the app
        Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.zjwh.android_wh_physicalfitness");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        device.wait(Until.hasObject(By.pkg("com.zjwh.android_wh_physicalfitness").depth(0)), 5000);

        //登录
        UiObject login = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_login"));
        login.click();
        //输入用户名
        device.wait(Until.findObject(By.textContains("校园号/手机号")), 5000);
        UiObject username = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/edt_user_name"));
        username.clearTextField();
        username.setText("18761993651");

        //输入密码
        UiObject pwd = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/edt_psw"));
        pwd.click();
        pwd.clearTextField();
        pwd.setText("111111");
        SystemClock.sleep(3000);

        UiObject submit = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_login"));
        //  device.pressBack();  //隐藏键盘
        submit.click();
        SystemClock.sleep(3000);

        device.wait(Until.findObject(By.textContains("立即开启校园")), 5000);

        UiObject openlife = device.findObject(new UiSelector().textContains("立即开启校园"));
        UiObject runplan = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/ll_runplan"));
        String[] Array = {"18862250206", "15906217298", "13140995776", "18626203266", "15906217298", "15850244170",
                "10000000005", "13140995776", "18918013166", "13382537375", "18606212060", "15862517775",
                "18761993651", "18118199622", "15996214586", "18962188894", "13814807676", "18051485520",
                "18796810064", "13914082313", "10000000004", "10000000003", "10000000002", "10000000001"};
        int i = 0;
        //如果没找到指定元素,则继续下一个账号进行登录
        while ((!openlife.exists()) && (!runplan.exists())) {
            username.clearTextField();
            username.setText(Array[i]);
            submit.click();
            i++;
            if (i >= 24) {
                i = 0;
            }
            if (openlife.exists() || runplan.exists()) {
                break;
            }

        }

        //关闭登录后弹出的介绍信息
        if (openlife.exists()) {
            openlife.click();
            SystemClock.sleep(3000);
        } else if (runplan.exists()) {
            runplan.click();
            SystemClock.sleep(3000);
        }
        //底部第二栏
        UiObject second = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/iv_versionplan"));

        //底部第一栏
        // UiObject runplan = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/ll_runplan"));

        //制定跑步计划
        UiObject set = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/left_image"));
        if (set.exists()) {
            set.click();
        }

        //  UiObject creat = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/btn_create_room"));

        device.pressBack();   //返回到主页面
        /*
        UiObject2 btn1 = device.findObject(By.text("").res(""));
        List<UiObject2> btnList = device.findObjects(By.res(""));
        btnList.get(1).click();
        */
        //我的个人信息
        UiObject me = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/iv_me"));
        //设置个性签名
        if (me.exists()) {
            me.click();
        }
        UiObject setting = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/iv_setting"));
        if (setting.exists()) {
            setting.click();
        }
        device.wait(Until.findObject(By.text("个性签名")), 5000);

        UiObject perSign = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/signature_txt"));
        if (perSign.exists()) {
            perSign.click();
        }
        SystemClock.sleep(3000);
        UiObject writeSign = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/signature_et"));
        if (writeSign.exists()) {
            writeSign.click();
            writeSign.clearTextField();
            writeSign.setText("hello !");
        }
        UiObject sub = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/action_edit_submit"));
        if (sub.exists()) {
            sub.click();
            device.pressBack();  //再次返回首页
        }

        //修改院系
        setting.click();
        UiObject yx = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/department_txt"));
        if (yx.exists()) {
            yx.click();
            UiObject yxContent = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/signature_et"));
            yxContent.click();
            yxContent.clearTextField();
            yxContent.setText("testing");
            sub.click();
            device.pressBack();
        }

        //查看跑步记录
        me.click();
        UiObject record = device.findObject(new UiSelector().textContains("跑步记录"));
        if (record.exists()) {
            record.click();
            SystemClock.sleep(3000);
            device.pressBack();
        }

        //查看自己的今日排名
        me.click();
        UiObject his = device.findObject(new UiSelector().textContains("历史排名"));
        if (his.exists()) {
            his.click();
            UiObject tod = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/btn_today_rank"));
            SystemClock.sleep(3000);
            tod.click();
            device.pressBack();
        }

        //查看今日排行的第二种方式
        runplan.click();
        //   UiObject2 tod2 = device.findObjec
        UiObject tod2 = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/right_image"));

        if (tod2.exists()) {
            tod2.click();
            SystemClock.sleep(3000);
            device.pressBack();
        }

        //首页下划查看跑步步数,并查看跑步规则
        if (runplan.exists()) {
            runplan.click();
        } else {
            device.pressBack();
        }
        device.swipe(width / 2, height / 3, width / 2, height * 3 / 4, 100);
        UiObject runrule = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_run_rule"));
        if (runrule.exists()) {
            runrule.click();
            device.pressBack();
        }

        //版本计划如果未被点赞,则进行点赞操作点赞
        if (runplan.exists()) {
            second.click();
        } else {
            device.pressBack();
        }
        UiObject dz = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_click"));
        if (dz.exists() && dz.getText().contains("点赞")) {
            dz.click();
        }

        //退出账号
        me.click();
        setting.click();
        UiObject logoutBtn = device.findObject(new UiSelector().resourceId("com.zjwh.android_wh_physicalfitness:id/tv_logout"));
        logoutBtn.click();
        UiObject subout = device.findObject(new UiSelector().textContains("退出登录"));
        subout.click();

        UiCollection videos = new UiCollection(new UiSelector().className("android.widget.FrameLayout"));
        int count = videos.getChildCount(new UiSelector().className("android.widget.LinearLayout"));
        UiObject video = videos.getChildByText(new UiSelector().className("android.widget.LinearLayout"), "Cute Baby Laughing");
        video.click();
        UiObject checkBox = video.getChild(new UiSelector().className("android.widget.Checkbox"));
        if (!checkBox.isSelected()) {
            checkBox.click();
        }

        UiScrollable settingsItem = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject about = settingsItem.getChildByText(new UiSelector().className("android.widget.LinearLayout"), "About tablet");
        about.click();


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
