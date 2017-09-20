package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class DisposableManager {
    private static CompositeDisposable compositeDisposable;

    private DisposableManager(){

    }

    public static void add(Disposable disposable){
        getCompositeDisposable().add(disposable);
    }

    public static void dispose(){
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable(){
        if (compositeDisposable == null || compositeDisposable.isDisposed()){
            return new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
