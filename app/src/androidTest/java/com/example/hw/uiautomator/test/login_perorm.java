package com.example.hw.uiautomator.test;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hoo on 10/24 11:28
 * 邮箱：907486688@qq.com
 */

public class login_perorm {

    private UiDevice device;
    public Context context;
    public Instrumentation instrumentation;
    private int width;
    private int height;

    @Test
    public void testLogin(){
        writerText("/sdcard/id.txt","id","","");
    }


    /**
     * 写内容到指定的文件中
     */
    public void writerText(String path, String id,String type ,String swipe) {

        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(id+" ");
            bufferedWriter.write(type+" ");
            bufferedWriter.write(swipe+"\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读数据并操作
     */

    public void readTextPerform(String path) throws IOException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(path),"utf8");
        BufferedReader bufferedReader = new BufferedReader(read);
        int i = 0;
        String lineText = null;
        String[] myArray = new String[3];
        while (bufferedReader.readLine()!=null){

            lineText = bufferedReader.readLine();
        }
        read.close();
    }
    public void sd()throws IOException{
        int size = 3;
        int [] a = new int [size];
        int [] b = new int [size];
        float [] c = new float [size];
        BufferedReader br = new BufferedReader(new FileReader("D:\new2.txt"));
        String line = br.readLine();
        int i=0;//从0开始
        while(line!=null){
            String [] numbers = line.split(" ");//这个是跳过空格
            a[i]=Integer.valueOf(numbers[0]);
            b[i]=Integer.valueOf(numbers[1]);
            c[i]=Float.valueOf(numbers[2]);
            i++;
            line = br.readLine();
        }

        for(int num:a)System.out.print(num+" ");
        System.out.println();
        for(int num:b)System.out.print(num+" ");
        System.out.println();
        for(float num:c)System.out.print(num+" ");

        br.close();
    }
}
