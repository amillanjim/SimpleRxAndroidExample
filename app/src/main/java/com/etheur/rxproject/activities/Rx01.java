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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rx01 extends AppCompatActivity {

    private Disposable disposableValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx01);
        initView();
    }

    private void initView(){
        Utilities.toolbar(this, "RX01");
        disposable();
    }

    /**
     * El disposable es una función que nos permite eliminar la subscripción de un observable
     * con un observador. Esto nos permite manejar de una mejor manera el memory leak-.
     */
    private void disposable(){
        Observable<String> numerosObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposableValue = d;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // El dispose lo que hace es liberar la subscripción
        if (!disposableValue.isDisposed()) {
            disposableValue.dispose();
        }
    }
}