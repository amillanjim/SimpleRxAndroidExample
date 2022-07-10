package com.etheur.rxproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.etheur.rxproject.R;
import com.etheur.rxproject.adapters.HomeCardAdapter;
import com.etheur.rxproject.models.CardItem;
import com.etheur.rxproject.models.RectangleExample;
import com.etheur.rxproject.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private final List<CardItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView(){
        Utilities.toolbar(this, "RxJava Ejemplo");
        RectangleExample rectangle = new RectangleExample.Builder(10, 10).x(5).y(5).build();
        items.add(new CardItem(getResources().getString(R.string.RX00), getResources().getString(R.string.EjemploRX), new Rx00()));
        items.add(new CardItem("RX01 Disposable", "Esté es un ejemplo de disposable", new Rx01()));
        items.add(new CardItem("RX02 CompositeDisposable", "Esté es un ejemplo de compositedisposable", new Rx02()));
        items.add(new CardItem("RX03 Operadores", "Esté es un ejemplo de operadores", new Rx03()));
        RecyclerView rvCardItems = findViewById(R.id.rvCardItems);
        HomeCardAdapter homeCard = new HomeCardAdapter(items, item -> startActivity(new Intent(this, item.getClass())));
        rvCardItems.setAdapter(homeCard);
    }
}