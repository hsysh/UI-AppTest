package com.example.hw.uiautomator.util;

import android.app.Instrumentation;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by Hoo on 10/9 10:13
 * 邮箱：907486688@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class UiScrollableTest {
    Instrumentation instrumentation;
    UiDevice device;

    @Before
    public void setUp() {

        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);

    }

    @Test
    public void testUiScrollable() throws UiObjectNotFoundException {

        UiScrollable uis = new UiScrollable(new UiSelector().resourceId("com.testerhome.nativeandroid:id/rv_topic_list"));
        System.out.println("是否可滚动:" + uis.isScrollable());
        System.out.println("是否可滚动:" + uis.isLongClickable());

        //设置垂直滚动
        uis.setAsVerticalList();
        uis.scrollForward();

        uis.scrollBackward();
        //设置水平滚动
        uis.setAsHorizontalList();
        uis.scrollForward();
        uis.scrollBackward();

        //通过实例查找子元素,资源id为"com.testerhome.nativeandroid:id/tv_topic_title"下的第1个实例
        UiObject abc = uis.getChildByInstance(new UiSelector().resourceId("com.testerhome.nativeandroid:id/tv_topic_title"), 0);
        System.out.println("通过instance得到的值:" + abc.getText());

        //通过text属性滚动搜索子元素
        UiObject abc1 = uis.getChildByText(new UiSelector().resourceId("com.testerhome.nativeandroid:id/tv_topic_title"), "第二期 Testerhome 最佳文章奖金评选倒计时");
        System.out.println(abc1.getText());

        uis.setAsVerticalList();
        uis.scrollTextIntoView("xwa");

        //向开始位置滚动
        uis.scrollToBeginning(3);
        //向结束位置滚动
        uis.scrollToEnd(3);
    }

//        1，UiScrollable(UiSelector container)
// 获取滚动元素
//
//        2，UiScrollable setAsVerticalList()
//        设置垂直方向滚动
//
//        3，UiScrollable setAsHorizontalList()
//        设置水平方向滚动
//
//        4，boolean exists(UiSelector selector)
//        判断二个滚动元是否一致
//
//        5，UiObject getChildByDescription(UiSelector childPattern, String text)
//        通过content-desc属性搜索子元素
//        调用的就是getChildByDescription(UiSelector childPattern, String text, boolean allowScrollSearch) allowScrollSearch默认为true
//
//        6，UiObject getChildByDescription(UiSelector childPattern, String text, boolean allowScrollSearch)
//        通过content-desc属性滚动搜索子元素
//        allowScrollSearch
//
//        True 允许滚动
//
//        False 禁止滚动
//
//        7, UiObject getChildByInstance(UiSelector childPattern, int instance)
//        通过实例搜索子元素
//
////通过实例查找子元素,资源id为"com.testerhome.nativeandroid:id/tv_topic_title"下的第1个实例
//        UiObject  abc  = uis.getChildByInstance(new UiSelector().resourceId("com.testerhome.nativeandroid:id/tv_topic_title"),0);
//
//        8. UiObject getChildByText(UiSelector childPattern, String text)
//        通过text属性滚动搜索子元素
//        调用的就是getChildByText(UiSelector childPattern, String text, boolean allowScrollSearch)方法，默认为true
//
//        9，UiObject getChildByText(UiSelector childPattern, String text, boolean allowScrollSearch)
//        通过text属性滚动搜索子元素
//        allowScrollSearch
//
//        True 允许滚动
//
//        False 禁止滚动
//
//        10， boolean scrollDescriptionIntoView(String text)
//        通过content-desc属性搜索子元素
//        生成uiselector元素调用scrollIntoView((new UiSelector()).description(text));
//
//        11， boolean scrollIntoView(UiObject obj)
//        通过uiobject搜索子元素
//        生成uiselector元素调用scrollIntoView(obj.getSelector());
//
//        12， boolean scrollIntoView(UiSelector selector)
//        通过uiselector搜索元素
//        到达最大滚动次数后结束搜索
//        可以通过 setMaxSearchSwipes(int swipes)方法修改滚动次数
//
//        13，boolean scrollTextIntoView(String text)
//
//        通过子元素text属性搜索元素
//        调用的scrollIntoView((new UiSelector()).text(text)) 方法
//
//        14，boolean ensureFullyVisible(UiObject childObject)
//        向前滚动直到uiobject在可滚动的容器中完全可见
//
//        15，UiScrollable setMaxSearchSwipes(int swipes)
//        设置搜索最大滚动次数
//
//        16，int getMaxSearchSwipes()
//        获取当前最大滚动次数
//
//        17，boolean flingForward()
//        以步长为5向前滚动
//        调用scrollForward(5)
//
//        18，boolean scrollForward()
//        以步长为55向前滚动
//        调用scrollForward(55)
//
//        19，boolean scrollForward(int steps)
//        设置步长向前滚动
//
//        20，boolean flingBackward ()
//        以步长为5向后滚动
//        调用scrollBackward (5)
//
//        21，boolean scrollBackward ()
//        以步长为55向后滚动
//        调用scrollBackward (55)
//
//        22，boolean scrollBackward (int steps)
//        设置步长向后滚动
//
//        23，boolean scrollToBeginning(int maxSwipes, int steps)
//        设置次数和步长向开始位置滚动
//
//        24，boolean scrollToBeginning(int maxSwipes)
//        设置的次数向开始位置滚动，默认步长为55
//        调用的scrollToBeginning(int maxSwipes, int steps) steps默认传参55
//
//        25，boolean flingToBeginning(int maxSwipes)
//        设置的次数向开始位置滚动，默认步长为5
//        调用的scrollToBeginning(int maxSwipes, int steps) steps默认传参5
//
//        26, boolean scrollToEnd(int maxSwipes, int steps)
//        设置次数和步长向结束位置滚动
//
//        27， boolean scrollToEnd(int maxSwipes)
//        设置的次数向结束位置滚动，默认步长为55
//        调用的scrollToEnd(int maxSwipes, int steps) steps默认传参55
//
//        28， boolean flingToEnd(int maxSwipes)
//        设置的次数向结束位置滚动，默认步长为5
//        调用的scrollToEnd(int maxSwipes, int steps) steps默认传参5
//
//        29， double getSwipeDeadZonePercentage()
//        获取当前滚动坐标偏移比例
//
//        30， UiScrollable setSwipeDeadZonePercentage(double swipeDeadZonePercentage)
//        设置滚动坐标偏移比例
}
