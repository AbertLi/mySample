/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain fullScreenNotification copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package one.example.com.mysample.utile;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutors {
    private static AppExecutors mAppExecutors = new AppExecutors();

    public static AppExecutors getInstance() {
        return mAppExecutors;
    }

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final MainThreadExecutor mMainThread;

    private final Executor newCachedT;

    private final ScheduledExecutorService newScheduledThreadPool2;

    private AppExecutors(Executor diskIO, Executor networkIO, MainThreadExecutor mainThread, Executor abc,
            ScheduledExecutorService newScheduledThreadPool2) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
        this.newCachedT = abc;
        this.newScheduledThreadPool2 = newScheduledThreadPool2;
    }

    private AppExecutors() {
        this(Executors.newSingleThreadExecutor(),                   //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务,按照先进先出的顺序执行。
                Executors.newFixedThreadPool(5),        //创建一个定长线程池，支持定时及周期性任务执行。
                new MainThreadExecutor(),                           //主线程
                Executors.newCachedThreadPool(),                    //线程池为无限大,若线程都在执行遇到新线程启动则开辟新线程。
                Executors.newScheduledThreadPool(3)); //创建一个定长线程池，支持定时及周期性任务执行。
    }

    public static Executor diskIO() {
        return getInstance().mDiskIO;
    }

    public static Executor networkIO() {
        return getInstance().mNetworkIO;
    }

    public static Executor getNewCachedT() {
        return getInstance().newCachedT;
    }

    public static ScheduledExecutorService getNewScheduledThreadPool2() {
        return getInstance().newScheduledThreadPool2;
    }




    public static Executor mainThread() {
        return getInstance().mMainThread;
    }
    public static void postDelayMainThread(Runnable runnable, long delay) {
        getInstance().mMainThread.postDelay(runnable, delay);
    }
    public static void remoRunnableMainThread(Runnable runnable) {
        getInstance().mMainThread.removeRunnable(runnable);
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }

        public void removeRunnable(Runnable runnable) {
            mainThreadHandler.removeCallbacks(runnable);
        }

        public void postDelay(Runnable runnable, long delay) {
            mainThreadHandler.postDelayed(runnable, delay);
        }
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
