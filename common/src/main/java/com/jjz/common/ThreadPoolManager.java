package com.jjz.common;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolManager {
    private ExecutorService executor;
    private Handler handler = new Handler(Looper.getMainLooper());

    private ThreadPoolManager(){
        executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread thread = new Thread(r){
                    @Override
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        super.run();
                    }
                };
                return thread;
            }
        });
    }

    private static final ThreadPoolManager POOL_MANAGER = new ThreadPoolManager();

    public static ThreadPoolManager getInstance(){
        return POOL_MANAGER;
    }

    public void executeRunnable(Runnable runnable){
        executor.execute(runnable);
    }

    public <Result> void executeTask(final Task<Result> task){
        executeRunnable(new Runnable() {
            @Override
            public void run() {
                final Result result;
                try {
                    result = task.onRequest();
                    //if(t != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                task.onSuccess(result);
                            }
                        });
                    //}
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onError(e);
                        }
                    });
                }

            }
        });
    }

    public interface Task<Result> {
        /**
         * execute on background
         * @return
         * @throws Exception
         */
        @WorkerThread
        Result onRequest() throws Exception;

        /**
         * execute on ui thread
         * @param result
         */
        @UiThread
        void onSuccess(Result result);

        /**
         * execute on ui thread
         * @param exception
         */
        @UiThread
        void onError(Exception exception);
    }


}
