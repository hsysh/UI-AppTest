package com.example.hw.uiautomator.util;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

/**
 * Created by Hoo on 10/9 10:11
 * 邮箱：907486688@qq.com
 */
@RunWith(AndroidJUnit4.class)
public class UiBySelectorTest {

    Instrumentation instrumentation;
    UiDevice device;

    @Before
    public void setUp() {

        instrumentation = InstrumentationRegistry.getInstrumentation();
        device = UiDevice.getInstance(instrumentation);

    }

    @Test
    public void testUiObject1() throws UiObjectNotFoundException {

        UiObject2 object2;
        //通过包含字符进行选取
        object2 = device.findObject(By.textContains("Home"));
        object2.click();
        device.pressBack();
        device.pressBack();
        //休眠3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1");

        //通过匹配第一个字符进行选取
        object2 = device.findObject(By.textStartsWith("T"));
        object2.click();
        device.pressBack();
        device.pressBack();
        //休眠3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2");

        //通过匹配最后一个字符进行选取
        object2 = device.findObject(By.textEndsWith("e"));
        object2.click();
        device.pressBack();
        device.pressBack();
        //休眠3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3");

        //通过匹配text属性进行选取
        object2 = device.findObject(By.text("TesterHome"));
        object2.click();
        device.pressBack();
        device.pressBack();
        System.out.println("4");

        //通过正则表达式进行匹配选取
        Pattern pt = Pattern.compile("([0-9]|\\.|\\-)*");
        object2 = device.findObject(By.text(pt));
        System.out.println("是否存在按钮:" + object2.isEnabled());

        object2 = device.findObject(By.text("TesterHome"));
        object2.click();
        //休眠3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //通过class名称属性选取
        object2 = device.findObject(By.clazz("android.widget.ImageButton"));
        object2.click();
        device.pressBack();

        //通过package,class名称属性选取
        object2 = device.findObject(By.clazz("com.testerhome.nativeandroid", "android.widget.ImageButto"));
        object2.click();
        device.pressBack();

        object2 = device.findObject(By.desc("Open navigation drawer"));
        System.out.println("----" + object2.isEnabled());
        object2.click();

        //通过下面二个方法可以看出,他们定位到的是同一个linearlayout
        //通过元素深度进行选取
        object2 = device.findObject(By.depth(7));
        System.out.println("=============" + object2.getClassName());
        System.out.println("=============" + object2.getApplicationPackage());

        //通过子元素进行匹配
        object2 = device.findObject(By.hasDescendant(By.text("TesterHome")));
        System.out.println("=============" + object2.getClassName());
        System.out.println("=============" + object2.getApplicationPackage());
        object2.click();

    }
//    1，By.textContains(String substring)
//    通过text包含字符进行选取
//
//    2，By.textStartsWith(String substring)
//    通过text第一个字符进行选取
//
//    3，By.textEndsWith(String substring)
//    通过text最后一个字符进行选取
//
//    4，By.text(String substring)
//    通过text文本进行选取
//
//    5，By.text(Pattern textValue)
//    通过text正则式进行选取
//
//    6，BySelector clazz(String className)
//    通过class名称进行先取
//
//    7，BySelector clazz(String packageName, String className)
//    通过package/class名称进行选取
//
//    8，BySelector clazz(Class clazz)
//    通过class进行选取
//
//    9，BySelector clazz(Pattern className)
//    通过正则class名称进行选取
//
//    10，BySelector desc(String contentDescription)
//    通过content-desc进行选取
//
//    11，BySelector descContains(String substring)
//    通过包含content-desc进行选取
//
//    12，BySelector descStartsWith(String substring)
//    通过content-desc第一个字符进行选取
//
//    13，BySelector descEndsWith(String substring)
//    通过content-desc最后一个字符进行选取
//
//    14，BySelector desc(Pattern contentDescription)
//    通过正则content-desc进行选取
//
//    15，BySelector pkg(String applicationPackage)
//    通过package名称进行选取
//
//    16，BySelector pkg(Pattern applicationPackage)
//    通过正则package名称进行选取
//
//    17，BySelector res(String resourceName)
//    通过resource-id进行选取
//
//    18，BySelector res(String resourcePackage, String resourceId)
//    通过package,resource-id名称进行选取
//
//    19，BySelector res(Pattern resourceName)
//    通过正则resource-id进行选取
//
//    20，BySelector checkable(boolean isCheckable)
//    通过chekcable进行选取
//
//    21，BySelector checked(boolean isChecked)
//    通过checked进行选取
//
//    22，BySelector clickable(boolean isClickable)
//    通过clickable进行选取
//
//    23，BySelector enabled(boolean isEnabled)
//    通过enabled进行选取
//
//    24，BySelector focusable(boolean isFocusable)
//    通过focusabe进行选取
//
//    25，BySelector focused(boolean isFocused)
//    通过focused进行选取
//
//    26，BySelector longClickable(boolean isLongClickable)
//    通过long-clickable进行选取
//
//    27，BySelector scrollable(boolean isScrollable)
//    通过scrollable进行选取
//
//    28，BySelector selected(boolean isSelected)
//    通过selected进行选取
//
//    29，BySelector depth(int exactDepth)
//    通过元素深度进行选取
//
//    30， BySelector depth(int min, int max)
//    通过元素深度进行选取
//
//    31， BySelector minDepth(int min)
//    通过最大深度进行选取
//
//    32， BySelector maxDepth(int max)
//    通过最小深度进行选取
//
//    33， BySelector hasChild(BySelector childSelector)
//    通过是否存在子元素进行选取
//
//    34， BySelector hasDescendant(BySelector descendantSelector)
//    通过子元素进行匹配选取
//
//    35，public BySelector hasDescendant(BySelector descendantSelector, int maxDepth)
//    通过最大深度的子元素进行匹配选取

}
