package com.idealcn.hxchat.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * author:idealcn
 * date:16-8-7 下午9:50
 */
public class ChatThreadManager {

    public static void execute(Runnable task){
        ExecutorService pool = Executors.newFixedThreadPool(5);

        Future<?> future = pool.submit(task);

        if (future.isDone())
            future.cancel(true);
    }
}
