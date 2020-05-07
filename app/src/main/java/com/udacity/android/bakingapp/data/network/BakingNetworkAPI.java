package com.udacity.android.bakingapp.data.network;

import com.udacity.android.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BakingNetworkAPI {

    @GET("{jsonFileName}")
    Observable<List<Recipe>> fetchRecipes(@Path("jsonFileName") String jsonFileName);


}
