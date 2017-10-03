package ru.arink_group.deliveryapp.domain.interactors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kirillvs on 03.10.17.
 */

public abstract class UseCase <T, Params> {
    private CompositeDisposable disposables;

    UseCase() {
        this.disposables = new CompositeDisposable();
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
