package com.udacity.android.bakingapp.data;


import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executors to help run database related tasks off the main thread.
 *
 */
public class AppExecutors {

    private static final String LOG_TAG = AppExecutors.class.getSimpleName();

    private static AppExecutors sInstance;
    private static final Object LOCK = new Object();
    private final Executor mDiskExecutor;
    private final Executor mNetworkExecutor;


    private AppExecutors(Executor disk, Executor network) {
        mDiskExecutor = disk;
        mNetworkExecutor = network;
    }

    public static AppExecutors getInstance() {
        Log.d(LOG_TAG, "Getting AppExecutors instance.");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(2));
                Log.d(LOG_TAG, "AppExecutors instance created.");
            }
        }

        return sInstance;
    }

    public Executor getDiskExecutor() {
        return mDiskExecutor;
    }

    public Executor getNetworkExecutor() {
        return mNetworkExecutor;
    }
}
