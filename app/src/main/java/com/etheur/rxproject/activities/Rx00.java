package com.etheur.rxproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.etheur.rxproject.R;
import com.etheur.rxproject.utilities.Utilities;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rx00 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx00);
        initView();
    }

    public void initView(){
        Utilities.toolbar(this, "Rx00");
        observable();
    }

    /**
     * Observable: El Observable es una clase que nos permite crear un objeto de tipo observer, es decir, será
     * el objeto que será observado cada que tenga un cambio.
     * Observer: El Observer es una clase que nos permite crear un objeto de tipo observador, es decir, será
     * el encargado de escuchar los cambios del observable que esté suscrito a esté
     * Subscribe: El método subscribe nos permite subscribir un observable a un observador, de la misma forma
     * se puede indicar en que hilo se necesita ejecutar y en que hilo se necesita mostrar
     */
    public void observable(){
        Observable<String> numerosObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Utilities.showConsoleMessage("onSuscribe" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String number) {
                Utilities.showConsoleMessage("onNext" + " Número: " + number + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Utilities.showConsoleMessage("onError: " + e + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Utilities.showConsoleMessage("onComplete: Se han emitido todos los datos" + " Hilo: " + Thread.currentThread().getName());
            }
        };

        numerosObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(numerosObserver);
    }
}