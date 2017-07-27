package com.ywc.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author yanwe
 *         createTime 2017-04-2017/4/25 15:33
 */
public class ThreadPoolUtil {

    public static final Executor DEFAULT_THREAD_POOL;

    static {
        //设置线程池大小 N_threads = N_CPU * U_CPU * (1 + W/C)
        int maxThreadNum = Runtime.getRuntime().availableProcessors() * 1 * (1 + 95/5);
        DEFAULT_THREAD_POOL = Executors.newFixedThreadPool(maxThreadNum, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
    }

    public static Executor createThreadPool(int size){
        //设置线程池大小 N_threads = N_CPU * U_CPU * (1 + W/C)
        int maxThreadNum = Runtime.getRuntime().availableProcessors() * 1 * (1 + 95/5);
        return Executors.newFixedThreadPool(Math.min(size,maxThreadNum), runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
    }
}
