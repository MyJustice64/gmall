package com.atguigu.gamll.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: PZY
 * @date: 2023-02-09 14:18
 * @description: * 线程池核心点:复用机制/ -----
 * * 1。提前创建好固定的线程一直在运行状态----死循环实现
 * * 2.提交的线程任务缓存到一个并发队列集合中，交给我们正在运行的线程执行
 * * 3，正在运行的线程就从队列中获取该任务执行
 */
public class MyExecutors {
    private List<WorkThread> workThreads;
    private BlockingDeque<Runnable> runnableDeque;
    private boolean isRun=true;

    /*
    最大线程数
     */
    public MyExecutors(int maxThreadCount, int dequeSize) {
        //1,限制队列容量缓存
        runnableDeque = new LinkedBlockingDeque<Runnable>(dequeSize);
        //2，提前创建好固定的线程一直在运行状态----死循环实现
        workThreads = new ArrayList<WorkThread>(maxThreadCount);
        for (int i = 0; i < maxThreadCount; i++) {
            new WorkThread().start();
        }

    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            while (true || runnableDeque.size()>0) {
                Runnable runnable = runnableDeque.poll();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    public boolean execute(Runnable command) {
        return runnableDeque.offer(command);
    }

    public static void main(String[] args) {
        MyExecutors myExecutors = new MyExecutors(2, 20);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            myExecutors.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ","+finalI);
                }
            });
        }
        myExecutors.isRun=false;//关闭线程
    }
}
