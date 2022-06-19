package com.etheur.rxproject.utilities;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.etheur.rxproject.R;

import java.util.Objects;

public class Utilities {
    public static void toolbar(AppCompatActivity activity, String title){
        Toolbar toolbar = activity.findViewById(R.id.toolBar);
        activity.setSupportActionBar(toolbar);
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle(null);
        TextView tvTitle = toolbar.findViewById(R.id.tvTitleToolBar);
        tvTitle.setText(title);
        if (!activity.getClass().getSimpleName().equals("Home")) {
            ImageButton btnBack = activity.findViewById(R.id.btnBack);
            btnBack.setVisibility(View.VISIBLE);
            btnBack.setOnClickListener(view -> activity.onBackPressed());
        }
    }

    public static void showConsoleMessage(String message){
        Log.d(Constants.TAG, message);
    }
}
