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
import com.example.hw.uiautomator.util.Util;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Parenting {

    private UiDevice device;
    public Context context;
    private int width;
    private int height;
    public int minute = 6;
    public Instrumentation instrumentation;
    public String pkgsys = "com.yewyw.healthy";

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);
        context = instrumentation.getContext();
    }

    @Test
    public void Parenting() throws UiObjectNotFoundException {
        width = device.getDisplayWidth();
        height = device.getDisplayHeight();
        //启动APP
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        testUtil.startTest();
        testUtil.printLog("完成APP启动");
        testUtil.allowAuthority();
        //滑动屏幕进入APP内部
        if (device.hasObject(By.res("com.yewyw.healthy:id/rb_head"))) {
            testUtil.allowAuthority();
        } else {
            int p = 0;
            while (!device.hasObject(By.res("com.yewyw.healthy:id/yinBt"))) {
                swipeRightToLeft();
                testUtil.printLog("滑动欢迎页");
                SystemClock.sleep(500);
                p++;
                if (p > 4) {
                    break;
                }
            }
        }
        if (device.hasObject(By.res("com.yewyw.healthy:id/yinBt"))) {
            if (testUtil.performClickByResID("com.yewyw.healthy:id/yinBt")) {//点击体验新版进入
                testUtil.printLog("点击体验新版");
            } else {
                testUtil.printLog("点击体验新版失败");
            }
            SystemClock.sleep(3000);
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_mine")) {
            testUtil.printLog("点击我的");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_login")) {
                testUtil.printLog("点击登录按钮");
                SystemClock.sleep(2000);
                //登录
                String username = Util.fetchUserName();
                if (username == null) {
                    System.exit(1);
                }
                if (testUtil.inputTextByResId("com.yewyw.healthy:id/username", username)) {
                    testUtil.printLog("输入用户名");
                } else {
                    testUtil.printLog("输入用户名失败");
                }
                if (testUtil.performClickByResID("com.yewyw.healthy:id/image_pass_visiblity")) {
                    testUtil.printLog("点击明文显示");
                } else {
                    testUtil.printLog("明文显示失败");
                }
                if (testUtil.inputTextByResId("com.yewyw.healthy:id/password", "123456")) {
                    testUtil.printLog("输入密码");
                } else {
                    testUtil.printLog("输入密码失败");
                }

                if (testUtil.performClickByResID("com.yewyw.healthy:id/login_btn")) {
                    testUtil.printLog("点击登录");
                } else {
                    testUtil.printLog("点击我的失败");
                }
                SystemClock.sleep(2000);

            } else {
                testUtil.printLog("点击登录按钮失败");
            }
        } else {
            testUtil.printLog("点击我的失败");
        }
        long t1 = System.currentTimeMillis();//循环minute分钟
        int count = 0;
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > minute * 60 * 1000) {
                break;
            } else {
                testUtil.printLog("第" + (count + 1) + "次循环开始");
                if (!device.getCurrentPackageName().equals(pkgsys)) {//重启APP
                    testUtil.startTargetApp();
                }
                test();
                count++;
            }
        }
    }

    public void test() {
        TestUtil testUtil = new TestUtil(device, context, pkgsys);
        int k = 0;
        while (device.getCurrentPackageName().equals(pkgsys) && !testUtil.hasObjectByID("com.yewyw.healthy:id/rb_head")) {
            testUtil.pressBack();
            k++;
            if (k > 3) {
                testUtil.printLog("返回主页面失败");
                break;
            }
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_head")) {
            //首页
            testUtil.printLog("点击首页");
        } else {
            testUtil.printLog("点击首页失败");
        }
        int j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/tv_search")) {
            j++;
            swipeUpTobuttom();
            testUtil.printLog("滑动到搜索栏");
            SystemClock.sleep(1000);
            if (j > 3) {
                testUtil.printLog("滑动到搜索栏失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/viewpager")) {
            testUtil.printLog("点击首页横幅");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击首页横幅失败");
        }
        //搜索内容
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/tv_search")) {
            swipeUpTobuttom();
            SystemClock.sleep(2000);
            if (j > 3) {
                testUtil.printLog("查找搜索栏失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_search")) {
            testUtil.printLog("点击搜索框");
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_content", "新生儿")) {
                testUtil.printLog("输入搜索内容");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_search")) {
                    testUtil.printLog("点击搜索");
                } else {
                    testUtil.printLog("点击搜索失败");
                }
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
            }
        } else {
            testUtil.printLog("点击搜索框失败");
        }
        j = 0;
        while (!testUtil.hasObjectByText("知识库")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动知识库");
            if (j > 3) {
                testUtil.printLog("滑动知识库失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByText("知识库")) {
            testUtil.printLog("点击知识库");
            if (testUtil.performClickByText("资料库")) {
                testUtil.printLog("点击资料库");
            } else {
                testUtil.printLog("点击资料库失败");
            }
            if (testUtil.performClickByText("儿科")) {
                testUtil.printLog("点击儿科");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击儿科失败");
            }
            if (testUtil.performClickByText("育儿官方")) {
                testUtil.printLog("点击育儿官方");
            } else {
                testUtil.printLog("点击育儿官方失败");
            }
            if (testUtil.performClickByText("自主原创")) {
                testUtil.printLog("点击自主原创");
            } else {
                testUtil.printLog("点击自主原创失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击知识库失败");
        }
        j = 0;
        while (!testUtil.hasObjectByText("自助学习")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动自助学习");
            if (j > 3) {
                testUtil.printLog("滑动到自助学习失败");
                break;
            }
            j++;
        }
        SystemClock.sleep(2000);
        if (testUtil.performClickByText("自助学习")) {
            testUtil.printLog("点击自助学习");
            if (testUtil.performClickByText("资料库")) {
                testUtil.printLog("点击资料库");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击资料库失败");
            }
            if (testUtil.performClickByText("常见问题")) {
                testUtil.printLog("点击常见问题");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击常见问题失败");
            }
            if (testUtil.performClickByText("自主测评")) {
                testUtil.printLog("点击自主评测");
            } else {
                testUtil.printLog("点击自主评测失败");
            }
            if (testUtil.performClickByText("名师讲座")) {
                testUtil.printLog("点击名师讲座");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_flash")) {
                    testUtil.printLog("点击文章");
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                        testUtil.printLog("点击返回");
                    } else {
                        testUtil.printLog("点击返回失败");
                    }
                    SystemClock.sleep(1000);
                } else {
                    testUtil.printLog("点击文章失败");
                }
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击名师讲座失败");
            }
            if (testUtil.performClickByText("换一换")) {
                testUtil.printLog("点击换一换");
            } else {
                testUtil.printLog("点击换一换失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        }
        j = 0;
        while (!testUtil.hasObjectByText("专业测评")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动到专业测评");
            j++;
            if (j > 3) {
                testUtil.printLog("滑动到专业测评失败");
                break;
            }
        }
        if (testUtil.performClickByText("专业测评")) {
            testUtil.printLog("点击专业评测");
        } else {
            testUtil.printLog("点击专业评测失败");
        }
        if (testUtil.performClickByText("发表原创")) {
            testUtil.printLog("点击发表原创");
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_publish", "aaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaa")) {
                testUtil.printLog("输入原创文章内容");
            }
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_title", "asdfe")) {
                testUtil.printLog("输入原创文章标题");
            } else {
                testUtil.printLog("输入文章标题失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_first")) {
                testUtil.printLog("选择第一个标签");
            } else {
                testUtil.printLog("选择第一个标签失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_second")) {
                testUtil.printLog("选择第二个标签");
            } else {
                testUtil.printLog("选择第二个标签失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_publsih")) {
                testUtil.printLog("点击提交按钮");
            } else {
                testUtil.printLog("点击提交按钮失败");
            }
        }
        int p = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_head")) {
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
            p++;
            if (p > 3)
                testUtil.printLog("退出发表原创失败");
            break;
        }
        j = 0;
        while (!testUtil.hasObjectByText("行业热点")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动行业热点");
            j++;
            if (j > 3) {
                testUtil.printLog("滑动行业热点失败");
                break;
            }
        }
        if (testUtil.performClickByText("行业热点")) {
            testUtil.printLog("点击行业热点");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击行业热点失败");
        }
        j = 0;
        while (!testUtil.hasObjectByText("医界新闻")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动医界新闻");
            if (j > 3) {
                testUtil.printLog("滑动医界新闻失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByText("医界新闻")) {
            testUtil.printLog("点击医界新闻");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击医界新闻失败");
        }
        j = 0;
        while (!device.hasObject(By.text("政策解读"))) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动政策解读");
            if (j > 3) {
                testUtil.printLog("滑动政策解读失败");
                break;
            }
            j++;
        }
        SystemClock.sleep(2000);
        if (testUtil.performClickByText("政策解读")) {
            testUtil.printLog("点击政策解读");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击政策解读失败");
        }
        //帮助
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_tools")) {
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
            j++;
            if (j > 3) {
                testUtil.printLog("返回到帮助页面失败");
                break;
            }
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_tools")) {
            int i = 0;
            while (!testUtil.hasObjectByText("热门问题")) {
                swipeUpTobuttom();
                testUtil.printLog("滑动热门问题");
                SystemClock.sleep(1000);
                i++;
                if (i > 3) {
                    testUtil.printLog("滑动热门问题失败");
                    break;
                }
            }
            if (testUtil.performClickByText("热门问题")) {
                testUtil.printLog("点击热门问题");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击热门问题失败");
            }
            if (testUtil.performClickByText("使用问题")) {
                testUtil.printLog("点击使用问题");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击使用问题失败");
            }
            if (testUtil.performClickByText("使用手册")) {
                testUtil.printLog("点击使用手册");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            }
            if (testUtil.performClickByText("服务教程")) {
                testUtil.printLog("点击服务教程");

                if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_title")) {
                    testUtil.printLog("点击文章");
                    SystemClock.sleep(2000);
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                        testUtil.printLog("点击返回");
                    } else {
                        testUtil.printLog("点击返回失败");
                    }
                    SystemClock.sleep(1000);
                } else {
                    testUtil.printLog("点击文章失败");
                }
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击服务教程失败");
            }
            j = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/imageView1")) {
                swipeButtomToUp();
                SystemClock.sleep(1000);
                testUtil.printLog("滑动每日文章");
                if (j > 3) {
                    testUtil.printLog("滑动到每日文章失败");
                    break;
                }
                j++;
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/imageView1")) {
                testUtil.printLog("点击每日文章");
                if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                    testUtil.printLog("点击返回");
                } else {
                    testUtil.printLog("点击返回失败");
                }
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击每日文章失败");
            }
        }
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_rob")) {
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
            j++;
            if (j > 3) {
                testUtil.printLog("返回到抢单页面失败");
                break;
            }
        }
        //抢单
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_rob")) {
            testUtil.printLog("点击抢单");
        } else {
            testUtil.printLog("点击抢单失败");
        }
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_exchange")) {
            testUtil.pressBack();
            SystemClock.sleep(1000);
            j++;
            if (j > 3) {
                testUtil.printLog("返回到兑换页面失败");
                break;
            }
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_exchange")) {
            testUtil.printLog("点击兑换");
            int m = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_exchange")) {
                m++;
                testUtil.pressBack();
                SystemClock.sleep(1000);
                if (m > 3) {
                    testUtil.printLog("退出兑换失败");
                    break;
                }
            }
        } else {
            testUtil.printLog("点击兑换失败");
        }
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_mine")) {
            testUtil.pressBack();
            SystemClock.sleep(1000);
            j++;
            if (j > 3) {
                testUtil.printLog("返回到我的页面失败");
                break;
            }
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rb_mine")) {
            testUtil.printLog("点击我的");
        } else {
            testUtil.printLog("点击我的失败");
        }
        SystemClock.sleep(2000);
        //添加常用语
        k = 0;
        while (!testUtil.hasObjectByText("添加常用语")) {
            swipeUpTobuttom();
            testUtil.printLog("滑动到添加常用语");
            k++;
            SystemClock.sleep(1000);
            if (k > 3) {
                testUtil.printLog("滑动到添加常用语失败");
                break;
            }
        }
        if (testUtil.performClickByText("添加常用语")) {
            testUtil.printLog("点击添加常用语");
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_question", "asdasd")) {
                testUtil.printLog("输入常用语");
            } else {
                testUtil.printLog("输入常用语失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/bt_commit")) {
                testUtil.printLog("点击提交");
                SystemClock.sleep(2000);
            } else {
                testUtil.printLog("点击提交失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
        } else {
            testUtil.printLog("点击添加常用语失败");
        }
        //查看消息通知
        if (testUtil.performClickByText("消息通知")) {
            testUtil.printLog("点击消息通知");
            if (testUtil.performClickByText("工单消息")) {
                testUtil.printLog("点击工单消息");
            } else {
                testUtil.printLog("点击工单消息失败");
            }
            if (testUtil.performClickByText("联系人消息")) {
                testUtil.printLog("点击联系人消息");
            } else {
                testUtil.printLog("点击联系人消息失败");
            }
            if (testUtil.performClickByText("系统通知")) {
                testUtil.printLog("点击系统通知");
            } else {
                testUtil.printLog("点击系统通知失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
        }
        //查看评论
        if (testUtil.performClickByText("我的评论")) {
            testUtil.printLog("点击我的评论");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
        } else {
            testUtil.printLog("点击我的评论失败");
        }
        j = 0;
        while (!testUtil.hasObjectByText("我的发表")) {
            swipeButtomToUp();
            testUtil.printLog("滑动我的发表");
            if (j > 3) {
                break;
            }
            j++;
        }
        if (testUtil.performClickByText("我的发表")) {
            testUtil.printLog("点击我的发表");
            if (testUtil.performClickByText("已通过")) {
                testUtil.printLog("点击已通过");
            } else {
                testUtil.printLog("点击已通过失败");
            }
            if (testUtil.performClickByText("审核中")) {
                testUtil.printLog("点击审核");
            } else {
                testUtil.printLog("点击审核失败");
            }
            if (testUtil.performClickByText("未通过")) {
                testUtil.printLog("点击未通过");
            } else {
                testUtil.printLog("点击未通过失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击我的发表失败");
        }
        j = 0;
        while (!testUtil.hasObjectByText("我的收藏")) {
            swipeButtomToUp();
            testUtil.printLog("滑动到我的收藏");
            if (j > 3) {
                testUtil.printLog("滑动到我的收藏失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByText("我的收藏")) {
            testUtil.printLog("点击我的收藏");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击我的收藏失败");
        }
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/relay_invite")) {
            swipeButtomToUp();
            testUtil.printLog("滑动邀请");
            if (j > 3) {
                break;
            }
            j++;
            if (testUtil.performClickByResID("com.yewyw.healthy:id/relay_invite")) {
                testUtil.printLog("点击邀请");
                if (testUtil.hasObjectByText("邀请医生")) {
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                        testUtil.printLog("点击返回");
                    } else {
                        testUtil.printLog("点击返回失败");
                    }
                    SystemClock.sleep(1000);
                } else {
                    testUtil.printLog("退出邀请失败");
                }
            } else {
                testUtil.printLog("点击我的邀请失败");
            }
        }
        j = 0;
        while (!testUtil.hasObjectByText("问题反馈")) {
            swipeButtomToUp();
            testUtil.printLog("滑动问题反馈");
            if (j > 3) {
                break;
            }
            j++;
        }
        if (testUtil.performClickByText("问题反馈")) {
            testUtil.printLog("点击问题反馈");
            SystemClock.sleep(2000);
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_question", "asdasdasddasdasdads")) {
                testUtil.printLog("输入反馈内容");
            } else {
                testUtil.printLog("输入反馈内容失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/bt_commit")) {
                testUtil.printLog("点击提交");
            } else {
                testUtil.printLog("点击提交失败");
            }
            SystemClock.sleep(2000);
        } else {
            testUtil.printLog("点击问题反馈失败");
        }
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/rb_mine")) {
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
            if (j > 3) {
                break;
            }
            j++;
        }
        //查看个人基本信息
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/img_myHead")) {
            swipeUpTobuttom();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动头像");
            if (j > 3) {
                break;
            }
            j++;
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/img_myHead")) {
            testUtil.printLog("点击头像");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_QR")) {
                testUtil.printLog("点击二维码");
            } else {
                testUtil.printLog("点击二维码失败");
            }
            if (testUtil.inputTextByResId("com.yewyw.healthy:id/etv_email", "123456@789.com")) {
                testUtil.printLog("输入邮箱");
            } else {
                testUtil.printLog("输入邮箱失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/image_birthday")) {
                testUtil.printLog("点击生日");
                SystemClock.sleep(2000);
                testUtil.pressBack();
            } else {
                testUtil.printLog("点击生日失败");
            }

            int ii = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/image_education")) {
                swipeButtomToUp();
                SystemClock.sleep(1000);
                testUtil.printLog("滑动学历");
                ii++;
                if (ii > 3) {
                    testUtil.printLog("滑动到学历失败");
                    break;
                }
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/image_education")) {//学历
                testUtil.printLog("点击学历");
            } else {
                testUtil.printLog("点击学历失败");
            }
            testUtil.pressBack();
            ii = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/image_workyear")) {
                swipeButtomToUp();
                testUtil.printLog("滑动工作年限");
                if (ii > 3) {
                    testUtil.printLog("滑动到工作年限失败");
                    break;
                }
                ii++;
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/image_workyear")) {//工作年限
                testUtil.printLog("点击工作年限");
            } else {
                testUtil.printLog("点击工作年限失败");
            }
            testUtil.pressBack();
            ii = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/relay_bank_card")) {
                swipeButtomToUp();
                SystemClock.sleep(1000);
                testUtil.printLog("滑动银行卡");
                ii++;
                if (ii > 3) {
                    testUtil.printLog("滑动到银行卡失败");
                    break;
                }
            }
            //绑定或者修改银行卡信息
            if (testUtil.performClickByResID("com.yewyw.healthy:id/relay_bank_card")) {
                testUtil.printLog("点击银行卡");
                if (testUtil.hasObjectByID("com.yewyw.healthy:id/updateImages")) {//修改信息
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/updateImages")) {
                        testUtil.printLog("点击修改");
                    } else {
                        testUtil.printLog("点击修改失败");
                    }
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_Upname", "Hoo")) {
                        testUtil.printLog("输入姓名");
                    } else {
                        testUtil.printLog("输入姓名失败");
                    }
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_UpbankNum", "123456")) {
                        testUtil.printLog("输入卡号");
                    } else {
                        testUtil.printLog("输入卡号失败");
                    }
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_UpbankName", "asd")) {
                        testUtil.printLog("输入银行名称");
                    } else {
                        testUtil.printLog("输入银行名称失败");
                    }
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/Bt_update_bank")) {
                        testUtil.printLog("提交");
                    } else {
                        testUtil.printLog("点击提交失败");
                    }
                } else if (testUtil.hasObjectByID("com.yewyw.healthy:id/Bt_finish_bank")) {//添加信息
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_name", "Hoo")) {
                        testUtil.printLog("输入姓名");
                    } else {
                        testUtil.printLog("输入姓名失败");
                    }
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_bankNum", "123456")) {
                        testUtil.printLog("输入卡号");
                    } else {
                        testUtil.printLog("输入卡号失败");
                    }
                    if (testUtil.inputTextByResId("com.yewyw.healthy:id/input_bankName", "asd")) {
                        testUtil.printLog("输入银行名称");
                    } else {
                        testUtil.printLog("输入银行名称失败");
                    }
                    if (testUtil.performClickByResID("com.yewyw.healthy:id/Bt_finish_bank")) {
                        testUtil.printLog("提交");
                    } else {
                        testUtil.printLog("点击提交失败");
                    }
                }
                testUtil.pressBack();
                SystemClock.sleep(1000);
            } else {
                testUtil.printLog("点击银行卡失败");
            }
            int jj = 0;
            while (!testUtil.hasObjectByID("com.yewyw.healthy:id/tv_commit") && !testUtil.hasObjectByID("com.yewyw.healthy:id/rb_mine")) {
                testUtil.pressBack();
                jj++;
                if (jj > 3) {
                    break;
                }
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_commit")) {//提交
                testUtil.printLog("提交");
            } else {
                testUtil.printLog("点击提交失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {//返回
                testUtil.printLog("返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
        }
        //切换离线在线状态
        if (testUtil.performClickByResID("com.yewyw.healthy:id/rbt_downline")) {
            SystemClock.sleep(2000);
            testUtil.printLog("切换为离线");
            testUtil.pressBack();
//            if (testUtil.performClickByResID("com.yewyw.healthy:id/tv_sure")) {
//                testUtil.printLog("切换为离线");
//            } else {
//                testUtil.printLog("切换离线失败");
//            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/rbt_online")) {
                testUtil.printLog("切换为在线");
            } else {
                testUtil.printLog("切换在线失败");
            }
        }
        //查看设置
        j = 0;
        while (!testUtil.hasObjectByID("com.yewyw.healthy:id/relay_setting")) {
            swipeButtomToUp();
            SystemClock.sleep(1000);
            testUtil.printLog("滑动到设置");
            if (j > 3) {
                testUtil.printLog("滑动到设置失败");
                break;
            }
            j++;
        }
        if (testUtil.performClickByResID("com.yewyw.healthy:id/relay_setting")) {
            testUtil.printLog("点击设置");
            if (testUtil.performClickByResID("com.yewyw.healthy:id/relay_abooutus")) {
                testUtil.printLog("点击关于我们");
            } else {
                testUtil.printLog("点击关于我们失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            if (testUtil.performClickByResID("com.yewyw.healthy:id/img_return")) {
                testUtil.printLog("点击返回");
            } else {
                testUtil.printLog("点击返回失败");
            }
            SystemClock.sleep(1000);
        } else {
            testUtil.printLog("点击设置失败");
        }
    }

    //向左滑
    public void swipeRightToLeft() {
        device.swipe(width * 7 / 8, height / 2, width / 8, height / 2, 20);
    }

    //下拉
    public void swipeUpTobuttom() {
        device.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 20);
    }

    //上滑
    public void swipeButtomToUp() {
        device.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 20);
    }

}
