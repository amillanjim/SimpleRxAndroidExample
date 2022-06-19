package com.etheur.rxproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.etheur.rxproject.R;
import com.etheur.rxproject.utilities.Utilities;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rx03 extends AppCompatActivity {

    private String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx03);
        initView();
    }

    private void initView(){
        Utilities.toolbar(this, "Rx03");
        observableOperators();
    }


    /**
     * Operadores que crean observables.
     * Just(): El operador just toma un lista de argumentos y convierte los elementos en elementos
     *         observables, con un máximo de 10 elementos.
     */
    private void observableOperators(){
        //testJust();
        //testJustArray();
        //testFromArray();
        //testRange();
        testRepeat();
    }

    /**
     * El método justo ejecuta los elementos asignados de manera sincrona, puede aceptar hasta
     * 10 argumentos.
     */
    private void testJust(){
        Utilities.showConsoleMessage("=============== Operador Just ===============");
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull String s) {
                        Utilities.showConsoleMessage("Just -> onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * Con el método justArray pasamos todos los elementos como un array y el método solo tiene
     * un argumento que es el array de objetos.
     */
    private void testJustArray(){
        Utilities.showConsoleMessage("=============== Operador JustArray ===============");
        Observable.just(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(String @NonNull [] strings) {
                        Utilities.showConsoleMessage("JustArray -> onNext: " + strings.length);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * Con el método fromArray nos crea un conjunto de elementos utilizando un iterador, es decir
     * cada elemnto del array es emitido uno a uno
     */
    private void testFromArray(){
        Utilities.showConsoleMessage("=============== Operador FromArray ===============");
        Observable.fromArray(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Utilities.showConsoleMessage("FromArray -> onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * El método Range crea un observable a partir de una secuencia de enteros generados,
     * le pasamos el valor inicial y el valor de la secuencia
     */
    private void testRange(){
        Utilities.showConsoleMessage("=============== Operador Range ===============");
        Observable.range(7, 17)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Utilities.showConsoleMessage("Range -> onNext" + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * Crea un Observable que emite una serie de elementos repetidamente hasta el infinito
     * además se le puede pasar un argumento para limitar el número de peticiones.
     */
    private void testRepeat(){
        Utilities.showConsoleMessage("=============== Operador Repeat ===============");
        Observable.range(7, 17)
                .repeat(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Utilities.showConsoleMessage("onNextRepeat: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}