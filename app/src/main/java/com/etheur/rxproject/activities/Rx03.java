package com.etheur.rxproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.etheur.rxproject.R;
import com.etheur.rxproject.models.Empleado;
import com.etheur.rxproject.models.ISum;
import com.etheur.rxproject.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rx03 extends AppCompatActivity {

    private String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        //testRepeat();
        //testCreate();
        //testInterval();
        //testLongTask();
        //testBufferOperator();
        //testMapOperator();
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

    private void testCreate(){
        Utilities.showConsoleMessage("=============== Operador Create ===============");
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                emitter.onNext("A");
                emitter.onNext("L");
                emitter.onNext("E");
                emitter.onNext("X");
                emitter.onNext("");
                emitter.onNext("M");
                emitter.onNext("I");
                emitter.onNext("L");
                emitter.onNext("L");
                emitter.onNext("A");
                emitter.onNext("N");
            } catch (Exception e){
                Utilities.showConsoleMessage("error en create");
                emitter.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull String s) { Utilities.showConsoleMessage("onNextCreate: " + s); }

                    @Override
                    public void onError(@NonNull Throwable e) { Utilities.showConsoleMessage("onErrorCreate: " + e.getMessage()); }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * Interval crea un observable que emite una secuencia de items espaciados por intervalo de
     * tiempo dado
     * take tomara o indicara los elementos o las veces que se debe de repetir
     */
    private void testInterval(){
        Utilities.showConsoleMessage("=============== Operador Interval ===============");
        Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Utilities.showConsoleMessage("onNextIterval: " + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private String longOperation() throws InterruptedException {
        Thread.sleep(100000);
        return "Finish";
    }

    /**
     * Con RxJava podemos hacer tareas asincronas de manera más sencilla, ya no es necesario hacer
     * el AsyncTask por ejemplo.
     */
    private void testLongTask(){
        Utilities.showConsoleMessage("=============== Operador Long Task ===============");
        Disposable disposable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                emitter.onNext(longOperation());
            } catch (InterruptedException e){
                emitter.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> Utilities.showConsoleMessage("onNextLongTask: " + s),
                        e -> Utilities.showConsoleMessage("onErrorLongTask: " + e),
                        () -> Utilities.showConsoleMessage("onCompleteLongTask")
                );
        compositeDisposable.add(disposable);
    }

    ISum sum = (a, b) -> a + b;
    //ISum sum = Integer::sum; //Method reference

    /**
     * Operador Buffer
     * El operador Buffer agrupo los items emitidos por un observable en lotes y emite dicho lote
     * en lugar de emitir los items por separado
     */
    private void testBufferOperator(){
        Utilities.showConsoleMessage("=============== Operador Buffer Operator ===============");
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<Integer> integers) {
                        Utilities.showConsoleMessage("onNextBuffer: " + integers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Utilities.showConsoleMessage("onErrorBuffer: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Utilities.showConsoleMessage("onCompleteBuffer");
                    }
                });
    }

    /**
     * El operador Map transforma cada item emitido por un Observable aplicando una función a cada
     * item.
     */
    private void testMapOperator()  {
        Utilities.showConsoleMessage("=============== Operador Map Operator ===============");
        List<Empleado> empleados = Empleado.setNewEmpleados();
        // Una intefaz Function en esté caso recibe una lista de tipo Empleados
        // y retorna una Lista de tipo String
        Observable.fromArray(empleados)
                .map(empleados1 -> {
                    List<String> nombres = new ArrayList<>();
                    for (Empleado empleado: empleados1){
                        nombres.add(empleado.getName());
                    }
                    return nombres;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<String> strings) {
                        Utilities.showConsoleMessage("onNextMapOperator: " + strings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Utilities.showConsoleMessage("onErrorMapOperator: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Utilities.showConsoleMessage("onCompleteMapOperator");
                    }
                });
    }

    /**
     *
     */
    private void testGroupBy(){
        Observable<Integer> numsObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable<GroupedObservable<String, Integer>> groupedObservable = numsObservable.groupBy(integer -> {
            if (integer %2 == 0) return "Par";
            else return "Impar";
        });
        groupedObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
                        stringIntegerGroupedObservable
                                .subscribe(new Observer<Integer>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {}

                                    @Override
                                    public void onNext(@NonNull Integer integer) {
                                        if (stringIntegerGroupedObservable.getKey().equals("Par"))
                                            Utilities.showConsoleMessage("onNext GroupBy: el número es par" + integer);
                                        else
                                            Utilities.showConsoleMessage("onNext GroupBy: el número es impar");
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {}

                                    @Override
                                    public void onComplete() {}
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

    }

    /**
     * El operador Scan transforma un item en otro item aplicando una función a cada item emitido por un
     * Observable y emite cada valor subsecuente.
     */
    private void testScan(){
        Disposable disposableScan = Observable.just(1, 2, 3, 4, 5, 6, 7)
                .scan((integer, integer2) -> integer + integer2)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(next -> Utilities.showConsoleMessage("onNextScan: " + next));
        compositeDisposable.add(disposableScan);
    }

    private void testWindow(){
        Utilities.showConsoleMessage("=============== Operador Window ===============");
        Observable<Observable<Integer>> observableObservable = Observable
                .range(1, 150)
                .window(3);
        observableObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(
                        observable -> {
                            Utilities.showConsoleMessage("onNext siguiente ventana");
                            observable.subscribe(n -> Utilities.showConsoleMessage("onNext item en ventana: " + n));
                        }
                );

    }

    /**
     * Solo emite un item por Observable si un tiempo en particular ha pasado sin que se haya emitido
     * otro item.
     */
    private void testDebounce(){
        Utilities.showConsoleMessage("=============== Operador Debounce ===============");
        TextInputEditText edtNumber = findViewById(R.id.edtNumber);
        Observable<String> observable = (Observable<String>) RxTextView.textChanges(edtNumber)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(e -> e.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> Utilities.showConsoleMessage("onNext - String de Busqueda: " + e)
                );
    }

    /**
     * Suprime los items duplicados emitidos por un Observable
     */
    private void testDistinct(){
        Utilities.showConsoleMessage("=============== Operador Distinct ===============");
        Observable<Integer> numerosOservable = Observable.just(1, 2, 3, 4, 5, 2, 3, 4, 1, 3);
        numerosOservable.distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext -> Utilities.showConsoleMessage("onNext Distinct: " + onNext));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}