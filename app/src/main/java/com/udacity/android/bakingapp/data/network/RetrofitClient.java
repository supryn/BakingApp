package com.udacity.android.bakingapp.data.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Wrapper class around Retrofit library.
 *
 */
final class RetrofitClient {

    private static final String LOG_TAG = RetrofitClient.class.getSimpleName();
    private static final String INSTANCE_CREATED = LOG_TAG + " instance created.";

    private static RetrofitClient sInstance;

    private static Retrofit mRetrofit;
    private static final Object LOCK = new Object();


    private RetrofitClient(Context context) {
        mRetrofit = new Retrofit.Builder()
            .baseUrl(BakingDataSource.BAKING_APP_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(createGsonConverter(context))
            .build();
    }

    static RetrofitClient getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RetrofitClient(context);
                Log.d(LOG_TAG, INSTANCE_CREATED);
            }
        }

        return sInstance;
    }

    private static Converter.Factory createGsonConverter(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(
                new TypeToken<List<Recipe>>() {}.getType(),
                new RecipeDeserializer(context));

        return GsonConverterFactory.create(gsonBuilder.create());
    }

    BakingNetworkAPI getNetworkService() {
        return mRetrofit.create(BakingNetworkAPI.class);
    }
}
