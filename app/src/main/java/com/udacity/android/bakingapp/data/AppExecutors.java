package com.udacity.android.bakingapp.data;


import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executors to help run database or network related tasks off the main thread.
 *
 */
public final class AppExecutors {

    private static final String LOG_TAG = AppExecutors.class.getSimpleName();
    private static final String INSTANCE_CREATED = LOG_TAG + " instance created.";

    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor mDiskExecutor;
    private final Executor mNetworkExecutor;

    private AppExecutors(Executor disk, Executor network) {
        mDiskExecutor = disk;
        mNetworkExecutor = network;
    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(2));
                Log.d(LOG_TAG, INSTANCE_CREATED);
            }
        }

        return sInstance;
    }

    Executor getDiskExecutor() {
        return mDiskExecutor;
    }

    Executor getNetworkExecutor() {
        return mNetworkExecutor;
    }
}
