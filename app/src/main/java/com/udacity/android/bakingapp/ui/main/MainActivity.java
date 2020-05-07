package com.udacity.android.bakingapp.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.udacity.android.bakingapp.R;
import com.udacity.android.bakingapp.utility.ViewModelInjectUtil;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModelFactory factory = ViewModelInjectUtil
                .provideMainActivityViewModelFactory(getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
        mViewModel.getRecipes().observe(this, recipes -> {

        });
    }
}
