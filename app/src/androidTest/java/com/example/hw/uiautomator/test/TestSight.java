package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;


import com.example.hw.uiautomator.util.TestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * 功能:测试视+AR
 *
 * @author: Hoo
 * Time: 11/23 11:05
 * Email：907486688@qq.com
 */

@RunWith(AndroidJUnit4.class)
public class TestSight {
    private UiDevice uiDevice;
    private Instrumentation instrumentation;
    private Context mContext;
    private TestUtil testUtil;
    private String tarPkgString;
    private int width;
    private int height;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        uiDevice = UiDevice.getInstance(instrumentation);
        mContext = instrumentation.getTargetContext();
        tarPkgString = "cn.easyar.sightplus";
        width = uiDevice.getDisplayWidth();
        height = uiDevice.getDisplayHeight();
        testUtil = new TestUtil(uiDevice, mContext,tarPkgString);
        testUtil.startTest();
    }

    @Test
    public void test() throws UiObjectNotFoundException {

        testUtil.allowAuthority();
        testUtil.printLog("进入APP");
        SystemClock.sleep(3000);
        //点击发现
        if (testUtil.hasObjectByID("cn.easyar.sightplus:id/rb_scan_found")) {
            if (testUtil.performClickByResID("cn.easyar.sightplus:id/rb_scan_found")) {
                testUtil.printLog("点击发现按钮");
                SystemClock.sleep(3000);
            } else {
                testUtil.printLog("没有点击发现按钮,任务结束");
                System.exit(1);
            }
        } else if (testUtil.hasObjectByID("cn.easyar.sightplus:id/scan_back_nav")) {
            if (testUtil.performClickByResID("cn.easyar.sightplus:id/scan_back_nav")) {
                testUtil.printLog("点击返回按钮");
                if (testUtil.performClickByResID("cn.easyar.sightplus:id/rb_scan_found")) {
                    testUtil.printLog("点击发现");
                    SystemClock.sleep(3000);
                } else {
                    testUtil.printLog("没有点击发现按钮,任务结束");
                    System.exit(1);
                }
            }
        } else {
            testUtil.printLog("没有进入到APP页面,任务结束");
            System.exit(1);

        }

        //点击五彩神牛
        uiDevice.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 20);
        SystemClock.sleep(3000);
        //  uiDevice.findObject(new UiSelector().resourceId("cn.easyar.sightplus:id/card_main_iv").instance(0)).click();
        if (testUtil.performClickByResIdandInstance("cn.easyar.sightplus:id/card_main_iv", 0)) {
            testUtil.printLog("点击五彩神牛图片");

            //点击玩一把
            if (testUtil.hasObjectByID("cn.easyar.sightplus:id/want_a_go")) {
                if (testUtil.performClickByResID("cn.easyar.sightplus:id/want_a_go")) {
                    testUtil.printLog("点击玩一把");
                    SystemClock.sleep(4000);
                    if (testUtil.hasObjectByID("cn.easyar.sightplus:id/show_photos")) {
                        //拍照
                        if (testUtil.performClickByResID("cn.easyar.sightplus:id/show_photos")) {
                            testUtil.allowAuthority();      //获取拍照权限
                            testUtil.printLog("点击拍照按钮");
                            SystemClock.sleep(2000);
                        } else {
                            testUtil.printLog("没有点击拍照按钮");
                        }
                        if (testUtil.hasObjectByID("cn.easyar.sightplus:id/show_gallery")) {
                            if (testUtil.performClickByResID("cn.easyar.sightplus:id/show_gallery")) {
                                testUtil.printLog("点击查看图片");
                                SystemClock.sleep(2000);
                                if (testUtil.hasObjectByID("cn.easyar.sightplus:id/gallery_back_nav")) {
                                    if (testUtil.performClickByResID("cn.easyar.sightplus:id/gallery_back_nav")) {
                                        testUtil.printLog("返回");
                                        SystemClock.sleep(2000);
                                    }
                                }
                            } else {
                                testUtil.printLog("没有点击查看图片");
                            }
                        } else {
                            testUtil.printLog("当前页面没有找到图片按钮");
                        }
                        //录影
                        if (testUtil.hasObjectByID("cn.easyar.sightplus:id/show_capture")) {
                            if (testUtil.performClickByResID("cn.easyar.sightplus:id/show_capture")) {
                                testUtil.allowAuthority();//获取录制视频权限
                                testUtil.printLog("点击录像按钮");
                                SystemClock.sleep(15000);//休眠15秒
                            } else {
                                testUtil.printLog("没有点击录像按钮");
                            }
                        } else {
                            testUtil.printLog("没有找到录像按钮");
                        }

                        //返回玩一把的界面
                        if (testUtil.hasObjectByID("cn.easyar.sightplus:id/show_cancel")) {
                            if (testUtil.performClickByResID("cn.easyar.sightplus:id/show_cancel")) {
                                testUtil.printLog("点击取消按钮,返回到玩一把界面");
                            }
                        } else {
                            testUtil.printLog("没有找到取消那妞");
                            testUtil.pressBack();
                        }
                        //返回主界面
                        if (testUtil.hasObjectByID("cn.easyar.sightplus:id/detail_back")) {
                            if (testUtil.performClickByResID("cn.easyar.sightplus:id/detail_back")) {
                                testUtil.printLog("点击返回按钮,返回到主界面");
                            }
                        } else {
                            testUtil.printLog("没有找到返回按钮");
                            testUtil.pressBack();
                        }
                    } else {
                        testUtil.printLog("没有找到拍照按钮");
                    }
                } else {
                    testUtil.printLog("没有点击玩一把");
                }
            } else {
                testUtil.printLog("没有找到玩一把按钮");
            }
        } else {
            testUtil.printLog("没有点击五彩神牛图片");
        }

        while (true) {
            if (uiDevice.hasObject(By.text("海豚"))) {
                break;
            }
            uiDevice.swipe(width / 2, height * 3 / 4, width / 2, height / 2, 20);
            boolean has = false;

            if (uiDevice.hasObject(By.text("海豚"))) {
                has = true;
                int heigth1 = uiDevice.findObject(By.text("海豚")).getVisibleBounds().bottom;
                if (uiDevice.click(width / 2, heigth1 - 200)) {
                    testUtil.printLog("点击海豚图片");
                    SystemClock.sleep(3000);
                    if (testUtil.hasObjectByID("cn.easyar.sightplus:id/want_a_go")) {
                        if (testUtil.performClickByResID("cn.easyar.sightplus:id/want_a_go")) {
                            testUtil.printLog("点击玩一把");
                            SystemClock.sleep(4000);
                            //点击海豚页面的立即购买按钮
                            if (testUtil.hasObjectByID("cn.easyar.sightplus:id/show_ll")) {
                                //获取坐标
                                int width2 = uiDevice.findObject(By.res("cn.easyar.sightplus:id/show_ll")).getVisibleBounds().left;
                                int height2 = uiDevice.findObject(By.res("cn.easyar.sightplus:id/show_ll")).getVisibleBounds().top;
                                //点击坐标上方的立即购买
                                if (uiDevice.click(width2, height2 - 5)) {
                                    testUtil.printLog("点击立即购买");
                                    SystemClock.sleep(4000);
                                    if (testUtil.hasObjectByID("cn.easyar.sightplus:id/nav_left")) {
                                        testUtil.performClickByResID("cn.easyar.sightplus:id/nav_left");
                                    }
                                } else {
                                    testUtil.printLog("坐标不正确,没有点击立即购买");
                                }
                            } else {
                                testUtil.printLog("没有进入到海豚页面");
                            }
                        } else {
                            testUtil.printLog("没有点击玩一把");
                        }
                    } else {
                        testUtil.printLog("没有找到玩一把按钮");
                    }
                } else {
                    testUtil.printLog("坐标不正确,没有点击海豚图片");
                }

                break;
            }
            if (has) {
                break;
            }
        }
    }
}
