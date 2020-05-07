package com.udacity.android.bakingapp.data.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.udacity.android.popularmovies.model.Movie;
import com.udacity.android.popularmovies.model.Review;
import com.udacity.android.popularmovies.model.Trailer;

import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class to build and retrieve a Retrofit instance.
 *
 */
public class RetrofitClient {

    private static final String LOG_TAG = RetrofitClient.class.getSimpleName();

    private static Retrofit mRetrofit;
    private static final Object LOCK = new Object();

    public static Retrofit getInstance(Context context) {
        Log.d(LOG_TAG, "Getting Retrofit instance.");
        if (mRetrofit == null) {
            synchronized (LOCK) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(MoviesDataSource.MOVIE_DB_BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(createGsonConverter(context))
                        .build();
                Log.d(LOG_TAG, "Created Retrofit instance.");
            }
        }

        return mRetrofit;
    }

    private static Converter.Factory createGsonConverter(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();

//        gsonBuilder.registerTypeAdapter(
//                new TypeToken<List<Movie>>() {}.getType(),
//                new MovieDetailsDeserializer(context));


        return GsonConverterFactory.create(gsonBuilder.create());
    }

}
