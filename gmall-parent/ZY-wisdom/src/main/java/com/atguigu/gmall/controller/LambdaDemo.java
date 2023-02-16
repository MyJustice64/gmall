package com.atguigu.gmall.controller;

/**
 * @author: PZY
 * @date: 2022/11/22 18:37
 * @description:
 */
public class LambdaDemo {

    /**
     * 成员内部类
     */
    class MyThread01 implements Runnable {
        @Override
        public void run() {
            System.out.println("成员内部类：用Lambda语法创建线程吧!");
        }
    }

    /**
     * 静态内部类
     */
    static class MyThread02 implements Runnable {
        @Override
        public void run() {
            System.out.println("静态内部类：对啊，用Lambda语法创建线程吧!");
        }
    }

    public static void main(String[] args) {

        /**
         * 局部内部类
         */
        class MyThread03 implements Runnable {
            @Override
            public void run() {
                System.out.println("局部内部类：用Lambda语法创建线程吧!");
            }
        }

        /**
         * 匿名内部类
         */
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("匿名内部类：求求你，用Lambda语法创建线程吧!");
            }
        };
        //成员内部类方式
        LambdaDemo lambdaDemo = new LambdaDemo();
        MyThread01 myThread01 = lambdaDemo.new MyThread01();
        new Thread(myThread01).start();
        //静态内部类方式
        MyThread02 myThread02 = new MyThread02();
        new Thread(myThread02).start();
        //局部内部类
        MyThread03 myThread03 = new MyThread03();
        new Thread(myThread03).start();
        //匿名内部类的方式
        new Thread(runnable).start();
    }
}

/*
         成员内部类：用Lambda语法创建线程吧!
        静态内部类：对啊，用Lambda语法创建线程吧!
        局部内部类：用Lambda语法创建线程吧!
        匿名内部类：求求你，用Lambda语法创建线程吧!
        https://blog.csdn.net/huangjhai/article/details/107110182?ops_request_misc=%257B%2522request%255Fid%2522%253A%2
        522166911297316782429716065%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166911297
        316782429716065&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-107
        110182-null-null.142^v66^control,201^v3^control_1,213^v2^t3_control1&utm_term=Lambda%E8%A1%A8%E8%BE%BE%E5%BC%8F
        &spm=1018.2226.3001.4187
        */
