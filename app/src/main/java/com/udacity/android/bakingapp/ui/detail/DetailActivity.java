package com.udacity.android.bakingapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.udacity.android.bakingapp.R;

public class DetailActivity extends AppCompatActivity {

    public static final String RECIPE_ID_KEY = "recipe_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
