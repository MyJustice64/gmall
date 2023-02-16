package com.atguigu.gamll.threads;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: PZY
 * @date: 2023-02-09 14:31
 * @description:
 */
public class Test06 {
    public static void main(String[] args) {
        //有界和无界区别:有界对容量有限制的 无界是没有限制 (integer.MAX_VALUE)
        LinkedBlockingDeque<String> strings = new LinkedBlockingDeque<String>(3);
        strings.add("祝勇");
        strings.add("小林");
        strings.add("秋子");
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());
    }
}