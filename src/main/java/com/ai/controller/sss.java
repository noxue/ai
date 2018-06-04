package com.ai.controller;

import java.util.Random;

public class sss {

    public static void main(String[] args) {
        String key = Math.random()+"";
      //  key.substring(2,17)
        System.out.println(key.substring(2,18));// 结果是个double类型的值，区间为[0.0,1.0）

        /*int num = (int) (Math.random() * 3); // 注意不要写成(int)Math.random()*3，这个结果为0，因为先执行了强制转换
        System.out.println("num=" + num);*/
    }
}
