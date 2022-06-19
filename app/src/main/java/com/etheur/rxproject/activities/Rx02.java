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
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rx02 extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx02);
        initView();
    }

    private void initView(){
        Utilities.toolbar(this, "RX02");
        compositeDisposable();
    }

    /**
     * Composite Disposable: El composite disposable nos sirve para hacer un grupo de observables
     * que puedan ser disposables al mismo tiempo, con normalidad se adjuntan los que pueden o deben
     * ser disposables al mismo tiempo.
     */
    private void compositeDisposable(){
        compositeDisposable = new CompositeDisposable();
        Observable<String> numeroObservable = Observable.just("1", "2", "3", "4", "5");
        Observable<String> numeroLetraObservable = Observable.just("uno", "dos", "tres", "cuatro", "cinco");

        // El DisposableObserver es una mezcla entre on Observer y un Disposable
        DisposableObserver<String> numeros = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Utilities.showConsoleMessage("onNextNumero: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Utilities.showConsoleMessage("onErrorNumero");
            }

            @Override
            public void onComplete() {
                Utilities.showConsoleMessage("onCompleteNumero");
            }
        };

        DisposableObserver<String> numerosLetras = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Utilities.showConsoleMessage("onNextLetra: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Utilities.showConsoleMessage("onErrorLetras");
            }

            @Override
            public void onComplete() {
                Utilities.showConsoleMessage("onCompleteLetras");
            }
        };

        compositeDisposable.add(
                numeroObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(numeros));

        compositeDisposable.add(
                numeroLetraObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(numerosLetras));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}